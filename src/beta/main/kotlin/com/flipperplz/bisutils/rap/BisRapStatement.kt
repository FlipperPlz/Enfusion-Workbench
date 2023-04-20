package com.flipperplz.bisutils.rap

abstract class BisRapStatement(override val mime: Byte?) : BisRapElement {
    override val subMime: Byte? = null
    override fun writeBinary(): ByteArray = byteArrayOf(mime ?: throw Exception("Rap statements REQUIRE a mime, dummy!"))
}