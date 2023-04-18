package com.flipperplz.bisutils.pbo

import com.flipperplz.bisutils.pbo.entries.BisPboEntry
import java.io.File
import java.io.RandomAccessFile

class BisPboFile private constructor(
    val entries: List<BisPboEntry<*>>
){
    companion object {

        fun parse(file: File): BisPboFile {
            val randomAccessFile = RandomAccessFile(file, "r")

            val entries = mutableListOf<BisPboEntry<*>>()

            var currentEntry: BisPboEntry<*>?

            do {
                currentEntry = BisPboEntry.create(randomAccessFile)
                currentEntry?.let { entries.add(it) }
            } while (currentEntry != null)


            return BisPboFile(entries)
        }

    }
}