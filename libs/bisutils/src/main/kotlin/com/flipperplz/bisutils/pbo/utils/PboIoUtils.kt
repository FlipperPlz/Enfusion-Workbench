package com.flipperplz.bisutils.pbo.utils

import com.flipperplz.bisutils.pbo.misc.BisPboProperty
import com.flipperplz.bisutils.utils.readAsciiZ
import java.io.RandomAccessFile


fun RandomAccessFile.seekToOffset(offset: Long) {
    if(offset != 0L && offset != filePointer)
        seek(offset)
}

fun RandomAccessFile.peekPboProperties(offset: Long = 0, keepRaw: Boolean): List<BisPboProperty> = readPboProperties(offset, keepRaw).also {
    if(offset != 0L) seek(filePointer - (it.calculateLength() + offset))
}

fun List<BisPboProperty?>.calculateLength(): Long = sumOf { it?.calculateLength() ?: 0 } + 1

fun RandomAccessFile.readPboProperties(offset: Long = 0, keepRaw: Boolean): List<BisPboProperty> = seekToOffset(offset).let {
    val properties = mutableListOf<BisPboProperty>()

    var propertyName: String
    while (readAsciiZ().also { propertyName = it }.isNotEmpty()) {
        val propertyValue: String = readAsciiZ()
        if(!keepRaw && (propertyName != "prefix" && propertyName != "name" && propertyName != "version" && propertyName == "obfuscated")) continue
        properties.add(BisPboProperty(propertyName, propertyValue))
    }

    return properties
}