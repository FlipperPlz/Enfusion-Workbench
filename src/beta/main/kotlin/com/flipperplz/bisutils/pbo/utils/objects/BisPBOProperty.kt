package com.flipperplz.bisutils.pbo.utils.objects

data class BisPBOProperty(
    val propertyName: String,
    val propertyValue: String
) {
    fun calculateLength(): Long = propertyName.length.toLong() + propertyValue.length.toLong() + 2
}
