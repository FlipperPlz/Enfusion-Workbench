package com.flipperplz.bisutils.utils

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset


fun ByteBuffer.getBytes(count: Int): ByteArray {
    val bytes = ByteArray(count) // 4 bytes for an integer
    get(bytes)

    return bytes
}

fun ByteBuffer.getInt(order: ByteOrder = ByteOrder.LITTLE_ENDIAN): Int = ByteBuffer.wrap(getBytes(4)).order(order).getInt(0)
fun ByteBuffer.getFloat(order: ByteOrder = ByteOrder.LITTLE_ENDIAN): Float = ByteBuffer.wrap(getBytes(4)).order(order).getFloat(0)

fun ByteBuffer.getAsciiZ(charset: Charset = Charsets.UTF_8): String {
    val builder = mutableListOf<Byte>()
    while (this.hasRemaining()) {
        val c = this.get()
        if(c == 0.toByte()) break
        builder.add(c)
    }
    return charset.decode(ByteBuffer.wrap(builder.toByteArray())).toString()
}

fun ByteBuffer.getCompactInt(): Int {
    var value: Int = 0
    var i: Int = 0
    while (true) {
        val v: Int = get().toInt()
        value = value or ((v and 0x7F) shl (7 * i))
        if ((v and 0x80) == 0) break
        ++i;
    }
    return value
}
