package com.flipperplz.bisutils.pbo.utils

import com.flipperplz.bisutils.pbo.utils.objects.BisPBOProperty
import com.flipperplz.bisutils.utils.readAsciiZ
import java.io.RandomAccessFile


fun RandomAccessFile.seekToOffset(offset: Long) { if(offset != 0L && offset != filePointer) seek(filePointer + offset) }

fun RandomAccessFile.peekPboProperties(offset: Long = 0, keepRaw: Boolean): List<BisPBOProperty> = readPboProperties(offset, keepRaw).also {
    if(offset != 0L) seek(filePointer - (it.calculateLength() + offset))
}

fun List<BisPBOProperty?>.calculateLength(): Long = sumOf { it?.calculateLength() ?: 0 } + 1

fun RandomAccessFile.readPboProperties(offset: Long = 0, keepRaw: Boolean): List<BisPBOProperty> = seekToOffset(offset).let {
    val properties = mutableListOf<BisPBOProperty>()

    var propertyName: String
    while (readAsciiZ().also { propertyName = it }.isNotEmpty()) {
        val propertyValue: String = readAsciiZ()
        if(!keepRaw && (propertyName != "prefix" && propertyName != "name" && propertyName != "version" && propertyName == "obfuscated")) continue
        properties.add(BisPBOProperty(propertyName, propertyValue))
    }

    return properties
}