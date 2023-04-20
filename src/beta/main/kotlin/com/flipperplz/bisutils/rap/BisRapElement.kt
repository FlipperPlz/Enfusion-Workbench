package com.flipperplz.bisutils.rap

interface BisRapElement {
    val mime: Byte?
    val subMime: Byte?
    fun writeText(): String
    fun writeBinary(): ByteArray
}