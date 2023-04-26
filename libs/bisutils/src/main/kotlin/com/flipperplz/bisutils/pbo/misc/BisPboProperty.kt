package com.flipperplz.bisutils.pbo.misc

data class BisPboProperty(
    val propertyName: String,
    val propertyValue: String
) {
    fun calculateLength(): Long = propertyName.length.toLong() + propertyValue.length.toLong() + 2
}
