package com.flipperplz.bisutils.core.io

import org.apache.commons.io.input.CountingInputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset

class BisInputStream(
    input: InputStream
) : CountingInputStream(input) {
    fun readByte(): Byte = read().toByte()
    fun readBytes(count: Int, endianness: ByteOrder = ByteOrder.BIG_ENDIAN): ByteArray {
        val buffer = (0 until count).map { readByte() }.toByteArray()
        return if(endianness == ByteOrder.LITTLE_ENDIAN) buffer.reversedArray() else buffer
    }

    fun readCompactInteger(): Int = generateSequence { read() }
        .takeWhile { it and 0x80 != 0 }
        .fold(0) { acc, v -> acc or (v and 0x7F shl (7 * acc)) }

    fun readAsciiZ(charset: Charset = Charsets.UTF_8): String = generateSequence { read() }
        .takeWhile { Char(it) != '\u0000' }
        .toList()
        .map { it.toByte()}
        .toByteArray()
        .let { String(it, charset) }

    fun readInt32(endianness: ByteOrder = ByteOrder.BIG_ENDIAN): Int = ByteBuffer.wrap(readBytes(4, endianness)).int
    fun readLong64(endianness: ByteOrder = ByteOrder.BIG_ENDIAN): Long = ByteBuffer.wrap(readBytes(8, endianness)).long
    fun readFloat(endianness: ByteOrder = ByteOrder.BIG_ENDIAN): Float = ByteBuffer.wrap(readBytes(4, endianness)).float

}