package com.flipperplz.bisutils.rap

import com.intellij.util.io.toByteArray
import java.nio.ByteBuffer

class BisRapString(value: String) : BisRapLiteral<String>(value, 1, 0) {
    override fun writeText(): String = "\"${value}\""
    override fun writeBinary(): ByteArray = super.writeBinary().plus(value.toByteArray(Charsets.UTF_8).plus(0))
}

class BisRapNumber(value: Number) : BisRapLiteral<Number>(value, 1) {

    override val subMime: Byte
        get() = when {
            isFloat -> 1
            isInteger -> 2
            else -> throw Exception("#4202023#")
        }

    val isInteger: Boolean
        get() = value is Int

    val isFloat: Boolean
        get() = value is Float

    val isDouble: Boolean
        get() = value is Double

    override fun writeText(): String = value.toString()

    override fun writeBinary(): ByteArray = super.writeBinary().plus(when(value) {
        is Int -> ByteBuffer.allocate(4).putInt(value).toByteArray()
        is Double -> ByteBuffer.allocate(8).putDouble(value).toByteArray()
        is Float -> ByteBuffer.allocate(4).putFloat(value).toByteArray()
        else -> throw Exception()
    })
}

class BisRapArray(
    value: List<BisRapLiteral<*>>
) : BisRapLiteral<List<BisRapLiteral<*>>>(value, null) {
    override val subMime: Byte = 3

    override fun writeText(): String = value.joinToString(prefix = "{", postfix = "}", separator = ", ") {
        it.writeText()
    }

    override fun writeBinary(): ByteArray {
        var binValue = super.writeBinary()

        value.forEach {
            binValue = binValue.plus(it.writeBinary())
        }

        return binValue;
    }

}