package com.flipperplz.bisutils.utils

import java.io.IOException
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset

fun RandomAccessFile.getAsciiZ(charset: Charset = Charsets.UTF_8): String = generateSequence { read() }
    .takeWhile { Char(it) != '\u0000' }
    .toList()
    .map { it.toByte()}
    .toByteArray()
    .let { String(it, charset) }

fun RandomAccessFile.pop() = seek(filePointer - 1)
fun RandomAccessFile.popInt() = seek(filePointer - 4)
fun RandomAccessFile.peek(): Byte =  readByte().also { pop() }
fun RandomAccessFile.peek(offset: Int): Byte {
    skipBytes(offset)
    return peek().also { seek(filePointer - offset) }
}

fun RandomAccessFile.peekInt(offset: Int): Int {
    skipBytes(offset)
    return peekInt().also { seek(filePointer - offset) }
}

fun RandomAccessFile.readBytes(count: Int): ByteArray = ByteArray(count).also { readFully(it) }

fun RandomAccessFile.peekAsciiZ(offset: Int): String {
    skipBytes(offset)
    return getAsciiZ().also { seek(filePointer - (offset + it.length + 1)) }
}

fun RandomAccessFile.peekBytes(count: Int, offset: Int): ByteArray {
    skipBytes(offset)
    val bytes =  readBytes(count)
    seek(filePointer - (offset + count))
    return bytes
}

fun RandomAccessFile.readInt32(endianness: ByteOrder = ByteOrder.LITTLE_ENDIAN): Int = ByteBuffer.wrap(readBytes(4)).order(endianness).int

fun RandomAccessFile.peekInt(): Int = readInt32().also { popInt() }
fun RandomAccessFile.peekAsciiZ(): String = getAsciiZ().also { seek(filePointer - (it.length + 1)) }

fun RandomAccessFile.readBisLZSS(expectedSize: Int, useSignedChecksum: Boolean = true): ByteArray {
    val N = 4096
    val F = 18
    val THRESHOLD = 2
    val text_buf = CharArray(N+F-1)
    val dst = ByteArray(expectedSize)

    if( expectedSize<=0 ) return dst

    val startPos = this.filePointer
    var bytesLeft = expectedSize
    var iDst = 0

    var i: Int
    var j: Int
    var r = N-F
    var flags = 0
    var c: Int
    var csum = 0

    fun incrementCSum(c: Int) {
        if (useSignedChecksum) csum += c.toByte() else csum += c
    }

    for( spaceOut in 0 until N-F ) text_buf[spaceOut] = ' '
    flags = 0
    while( bytesLeft > 0 ) {
        if( ((flags shr 1) and 256) == 0 ) {
            c = this.read()
            flags = c or 0xff00
        }

        if( (flags and 1) != 0) {
            c = this.read()
            incrementCSum(c)

            // save byte
            dst[iDst++] = c.toByte()
            bytesLeft--
            // continue decompression
            text_buf[r] = c.toChar()
            r++
            r = r and (N-1)
        } else {
            i = this.read()
            j = this.read()
            i = i or (j and 0xf0 shl 4)
            j = j and 0x0f
            j += THRESHOLD

            val ii = r-i
            val jj = j+ii

            if (j+1 > bytesLeft) throw IOException("LZSS overflow")

            for( x in ii..jj ) {
                c = text_buf[x and (N-1)].toInt()
                incrementCSum(c)

                // save byte
                dst[iDst++] = c.toByte()
                bytesLeft--
                // continue decompression
                text_buf[r] = c.toChar()
                r++
                r = r and (N-1)
            }
        }
    }
    val csData = ByteArray(4)
    this.read(csData)
    val csr = ByteBuffer.wrap(csData).int

    if( csr != csum ) throw IOException("Checksum mismatch")
    return dst
}

fun String.asciiZLength(): Int = length + 1

fun DoubleArray.isArrayOfZeros(): Boolean = all { it.toDouble() == 0.0 }

fun ByteArray.isArrayOfZeros(): Boolean = all { it == 0.toByte() }

fun ByteBuffer.getAsciiZ(charset: Charset = Charsets.UTF_8): String {
    val builder = StringBuilder()
    val decoder = charset.newDecoder()
    val terminator = ByteBuffer.allocate(1).put(0).flip()
    while (this.hasRemaining()) {
        val buffer = ByteBuffer.allocate(1)
        while (this.remaining() > 0 && this.compareTo(terminator) != 0) {
            buffer.put(this.get())
        }
        buffer.flip()
        val decoded: String = decoder.decode(buffer).toString()
        if (this.remaining() > 0) {
            this.get() // Consume the null terminator
        }
        if (decoded.isEmpty()) {
            break
        }
        builder.append(decoded)
    }
    return builder.toString()
}

fun ByteBuffer.getCompactInt(): Int {
    var value = 0
    for (i in 0..4) {
        val v = get().toInt() and 0xFF
        value = value or ((v and 0x7F) shl (7 * i))
        if ((v and 0x80) == 0) {
            break
        }
    }
    return value
}