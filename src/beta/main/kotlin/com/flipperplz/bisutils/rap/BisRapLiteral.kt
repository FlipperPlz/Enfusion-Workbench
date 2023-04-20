package com.flipperplz.bisutils.rap

abstract class BisRapLiteral<T>(
    val value: T,
    override val mime: Byte?,
    override val subMime: Byte? = null
) : BisRapElement {

    override fun writeBinary(): ByteArray {
        subMime?.let {
            return byteArrayOf(it)
        }

        return byteArrayOf()
    }
}
