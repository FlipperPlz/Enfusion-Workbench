package com.flipperplz.bisutils.pbo.entries

import com.flipperplz.bisutils.pbo.BisPboFile
import java.io.RandomAccessFile

sealed class  BisPboEntry<T> (
    val pboFile: BisPboFile,
    protected var mimeType: EntryMimeType,
    protected var fileName: String,
    protected var originalSize: Int,
    protected var offset: Int,
    protected var timestamp: Int,
    protected var blockSize: Int
) {
    var cached: T? = null
    abstract fun readData(reader: RandomAccessFile, keepRaw: Boolean): T

    class BisPboDataEntry(
        pboFile: BisPboFile,
        mimeType: EntryMimeType,
        fileName: String,
        originalSize: Int,
        offset: Int,
        timestamp: Int,
        blockSize: Int
    ) : BisPboEntry<ByteArray>(
        pboFile,
        mimeType,
        fileName,
        originalSize,
        offset,
        timestamp,
        blockSize
    ) {
        override fun readData(reader: RandomAccessFile, keepRaw: Boolean): ByteArray {
            TODO("Not yet implemented")
        }
    }

    class BisPboVersionEntry(
        pboFile: BisPboFile,
        val properties: List<BisPboVersionEntry.BisPBOProperty>
    ) : BisPboEntry<List<BisPboVersionEntry.BisPBOProperty>>(
        pboFile,
        EntryMimeType.VERSION,
        "",
        0,
        0,
        0,
        0
    ) {
        data class BisPBOProperty(
            val propertyName: String,
            val propertyValue: String
        )

        override fun readData(reader: RandomAccessFile, keepRaw: Boolean): List<BisPBOProperty> = TODO()

    }

    companion object {
        fun create(randomAccessFile: RandomAccessFile): BisPboEntry<*>? {

            TODO()
        }
    }
}