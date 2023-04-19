package com.flipperplz.bisutils.pbo

import com.flipperplz.bisutils.pbo.utils.calculateLength
import com.flipperplz.bisutils.pbo.utils.readPboProperties
import com.flipperplz.bisutils.pbo.utils.seekToOffset
import com.flipperplz.bisutils.pbo.misc.BisPboProperty
import com.flipperplz.bisutils.pbo.misc.EntryMimeType
import com.flipperplz.bisutils.utils.*
import kotlin.math.abs

sealed class BisPboEntry<T> protected constructor(
    val pboFile: BisPboFile, protected var metadataStart: Long, protected var mimeType: EntryMimeType,
    protected var fileName: String, protected var originalSize: Int, protected var offset: Int,
    protected var timestamp: Int, protected var packedSize: Int,
) {
    open var metadataLength: Long = 20 + fileName.asciiZLength().toLong()
    open var blockStart: Long = -1
    abstract var blockLength: Long protected set
    abstract val cachedBlock: T

    open fun getInfo(indent: Int = 0): String {
        val tabs = "\t".repeat(indent)
        return "filename = \"$fileName\",\n$tabs" +
                "metadataStart = $metadataStart,\n$tabs" +
                "metadataLength = $metadataLength,\n$tabs" +
                "blockStart = $blockStart,\n$tabs" +
                "blockLength = $blockLength,\n$tabs" +
                "mime = $mimeType,\n$tabs" +
                "offset = $offset,\n$tabs" +
                "timestamp = $timestamp,\n$tabs" +
                "originalSize = $originalSize,\n$tabs" +
                "packedSize = $packedSize$tabs"
    }

    open fun initializeBlock(file: BisRandomAccessFile) {
        blockStart = file.filePointer
        file.skipBytes(blockLength.toInt())
    }

    abstract fun readBlock(reader: BisRandomAccessFile, keepRaw: Boolean): T

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
        override var cachedBlock: Unit = Unit

        override fun initializeBlock(file: BisRandomAccessFile) { }

        override fun readBlock(reader: BisRandomAccessFile, keepRaw: Boolean) { }
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
            get() = if(originalSize == 0) false else originalSize != packedSize

        override var blockLength: Long = packedSize.toLong()

        override val cachedBlock: ByteArray
            get() = pboFile.getOrCreateCachedEntry(this) ?: throw Exception("Error caching $this")

        override fun getInfo(indent: Int): String = super.getInfo(indent) + ",\n${"\t".repeat(indent)}compressed = $compressed"

        override fun readBlock(reader: BisRandomAccessFile, keepRaw: Boolean): ByteArray {
            reader.seekToOffset(blockStart)

            if(keepRaw || !compressed) return reader.readBytes(blockLength.toInt())

            return reader.readBisLZSS(packedSize)
        }

        companion object {
            fun parse(pboFile: BisPboFile, file: BisRandomAccessFile, entryName: String? = null, keepRaw: Boolean = true): BisPboDataEntry? {
                val parsedName = entryName ?: file.readAsciiZ()
                val metadataStart = if(entryName != null) (file.filePointer - entryName.asciiZLength()) else file.filePointer
                val mimeType = EntryMimeType.fromMime(file.readInt32()) ?: return null
                val originalSize = abs(file.readInt32())
                val offset = if(keepRaw) abs(file.readInt32()) else 0
                val timestamp = if(keepRaw) abs(file.readInt32()) else 0
                val packedSize = abs(file.readInt32())
                return BisPboDataEntry(
                    pboFile = pboFile,
                    metadataStart = metadataStart,
                    mimeType = mimeType,
                    fileName = parsedName,
                    originalSize = originalSize,
                    offset = offset,
                    timestamp = timestamp,
                    packedSize = packedSize
                ).also {
                    if(!keepRaw) {
                        if(!it.compressed) it.originalSize = 0
                        it.mimeType = EntryMimeType.NORMAL_DATA
                    }
                }
            }
        }

    }

    class BisPboVersionEntry(
        pboFile: BisPboFile,
        entryStart: Long = 0,
        var properties: List<BisPboProperty>,

        ) : BisPboEntry<List<BisPboProperty>>(
        pboFile, entryStart, EntryMimeType.VERSION, "", 0, 0, 0, 0
    ) {
        override var metadataLength: Long = 20 + fileName.asciiZLength().toLong()

        override var blockLength: Long = properties.calculateLength()
        init {
            blockStart = entryStart + metadataLength
        }

        override fun getInfo(indent: Int): String {
            val tabs = "\t".repeat(indent);
            return super.getInfo(indent) + properties.joinToString(
                separator = ",\n$tabs\t",
                prefix = ",\n${tabs}properties = [\n$tabs\t",
                postfix = "\n$tabs]"
            ) { "\"" + it.propertyName + "\" = \"" + it.propertyValue + "\"" }
        }

        override fun initializeBlock(file: BisRandomAccessFile) { }

        constructor(pboFile: BisPboFile, file: BisRandomAccessFile, keepRaw: Boolean) : this(
            pboFile = pboFile,
            entryStart = file.filePointer,
            properties = file.readPboProperties(keepRaw = keepRaw)
        )

        override val cachedBlock: List<BisPboProperty>
            get() = properties

        override fun readBlock(reader: BisRandomAccessFile, keepRaw: Boolean): List<BisPboProperty> = reader.readPboProperties(keepRaw = keepRaw)

    }
    //0x73726550
    companion object {
        fun parse(file: BisPboFile, reader: BisRandomAccessFile, keepRaw: Boolean): BisPboEntry<*>? {
            val parsedName = reader.peekAsciiZ()
            val nameLength = parsedName.asciiZLength()
            val parsedMime = EntryMimeType.fromMime(reader.peekInt(nameLength)) ?: return null

            if(parsedMime == EntryMimeType.VERSION) {
                if(nameLength != 1) println("TODO Warning"); //TODO
                if(reader.peekBytes(16, 4 + nameLength).isArrayOfZeros()) println("TODO Warning"); //TODO
                return BisPboVersionEntry(file, reader.filePointer, reader.readPboProperties(20L + nameLength, keepRaw))
            }
            reader.skipBytes(nameLength)

            if(nameLength == 1 && parsedMime == EntryMimeType.DUMMY) {
                //we can spare the accidental 'rereading' of 16 bytes as entries without a name are uncommon
                if(reader.peekBytes(16, 4).isArrayOfZeros()) {
                    return BisPboDummyEntry(file, reader.filePointer).also { reader.skipBytes(21) }
                }
            }

            return BisPboDataEntry.parse(file, reader, parsedName, keepRaw)
        }

    }
}

