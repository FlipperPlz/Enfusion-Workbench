package com.flipperplz.bisutils.pbo.entries

import com.flipperplz.bisutils.pbo.BisPboFile
import com.flipperplz.bisutils.pbo.utils.calculateLength
import com.flipperplz.bisutils.pbo.utils.readPboProperties
import com.flipperplz.bisutils.pbo.utils.seekToOffset
import com.flipperplz.bisutils.pbo.utils.objects.BisPBOProperty
import com.flipperplz.bisutils.pbo.utils.objects.EntryMimeType
import com.flipperplz.bisutils.utils.*
import java.io.RandomAccessFile
import kotlin.math.abs

sealed class BisPboEntry<T> protected constructor(
    val pboFile: BisPboFile, protected var metadataStart: Long, protected var mimeType: EntryMimeType,
    protected var fileName: String, protected var originalSize: Int, protected var offset: Int,
    protected var timestamp: Int, protected var packedSize: Int,
) {
    protected var metadataLength: Long = 21
    protected open var blockStart: Long = -1
    protected abstract var blockLength: Long
    protected abstract var cachedBlock: T?

    init {
        metadataLength += fileName.length
    }
    open fun getMetadataLength(): Long = metadataLength

    open fun initializeBlock(reader: RandomAccessFile) {
        if(blockStart == -1L) throw Exception("already initialized")
        blockStart = reader.filePointer
        reader.skipBytes(blockLength.toInt())
    }

    abstract fun readBlock(reader: RandomAccessFile, keepRaw: Boolean): T

    class BisPboDummyEntry(
        pboFile: BisPboFile,
        position: Long
    ) : BisPboEntry<Unit>(
        pboFile = pboFile,
        metadataStart = position,
        mimeType = EntryMimeType.DUMMY,
        fileName = "",
        packedSize = 0,
        originalSize = 0,
        timestamp = 0,
        offset = 0
    ) {
        override var blockLength: Long = 0
        override var cachedBlock: Unit? = null

        override fun initializeBlock(reader: RandomAccessFile) { }
        override fun readBlock(reader: RandomAccessFile, keepRaw: Boolean) { }
    }

    class BisPboDataEntry(
        pboFile: BisPboFile, metadataStart: Long, mimeType: EntryMimeType,
        fileName: String, originalSize: Int, offset: Int, timestamp: Int,
        packedSize: Int,
    ) : BisPboEntry<ByteArray>(
        pboFile, metadataStart, mimeType, fileName,
        originalSize, offset, timestamp, packedSize
    ) {

        val compressed: Boolean
            get() = originalSize != 0 && originalSize == packedSize

        override var blockLength: Long = packedSize.toLong()

        override var cachedBlock: ByteArray? = pboFile.getCachedEntry<ByteArray>(this)
        override fun readBlock(reader: RandomAccessFile, keepRaw: Boolean): ByteArray {
            reader.seekToOffset(blockStart)

            if(keepRaw || !compressed) return reader.readBytes(blockLength.toInt())

            return reader.readBisLZSS(packedSize)
        }

        constructor(pboFile: BisPboFile, file: RandomAccessFile, entryName: String? = null, keepRaw: Boolean = true): this(
            pboFile = pboFile,
            metadataStart = if(entryName != null) (file.filePointer + entryName.asciiZLength()) else file.filePointer,
            mimeType = with(file.readInt()) { EntryMimeType.values().first { it.mime == this} },
            fileName = entryName ?: file.readAsciiZ(Charsets.UTF_8),
            originalSize = abs(file.readInt()),
            offset = if(keepRaw) abs(file.readInt()) else 0,
            timestamp = if(keepRaw) abs(file.readInt()) else 0,
            packedSize = abs(file.readInt())
        ) {
            if(!keepRaw) {
                if(!compressed) originalSize = 0
                mimeType = EntryMimeType.NORMAL_DATA
            }
        }

    }

    class BisPboVersionEntry(
        pboFile: BisPboFile,
        entryStart: Long = 0,
        var properties: List<BisPBOProperty>,

        ) : BisPboEntry<List<BisPBOProperty>>(
        pboFile, entryStart, EntryMimeType.VERSION, "", 0, 0, 0, 0
    ) {
        override var blockLength: Long = properties.calculateLength()
        init {
            blockStart = metadataStart + metadataLength
        }

        override fun initializeBlock(reader: RandomAccessFile) { }

        constructor(pboFile: BisPboFile, file: RandomAccessFile, keepRaw: Boolean) : this(
            pboFile = pboFile,
            entryStart = file.filePointer,
            properties = file.readPboProperties(keepRaw = keepRaw)
        )

        override var cachedBlock: List<BisPBOProperty>? = mutableListOf()

        override fun readBlock(reader: RandomAccessFile, keepRaw: Boolean): List<BisPBOProperty> = reader.readPboProperties(keepRaw = keepRaw)

        override fun getMetadataLength(): Long = metadataLength + blockLength

    }

    companion object {
        fun parse(file: BisPboFile, reader: RandomAccessFile, keepRaw: Boolean): BisPboEntry<*>? {
            val parsedName = reader.peekAsciiZ()
            val nameLength = parsedName.asciiZLength()
            val parsedMime = EntryMimeType.fromMime(reader.peekInt(nameLength)) ?: return null

            if(parsedMime == EntryMimeType.VERSION) {
                if(nameLength != 0) println("TODO Warning"); //TODO
                if(reader.peekBytes(16, 4 + nameLength).isArrayOfZeros()) println("TODO Warning"); //TODO
                return BisPboVersionEntry(file, reader.filePointer, reader.readPboProperties(20L + nameLength, keepRaw))
            }
            reader.skipBytes(nameLength)

            if(nameLength == 0 && parsedMime == EntryMimeType.DUMMY) {
                //we can spare the accidental 'rereading' of 16 bytes as entries without a name are uncommon
                if(reader.peekBytes(16, 4).isArrayOfZeros()) return BisPboDummyEntry(file, reader.filePointer)
            }

            return BisPboDataEntry(file, reader, parsedName, keepRaw)
        }

    }
}

