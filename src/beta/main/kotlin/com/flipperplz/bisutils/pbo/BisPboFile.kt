package com.flipperplz.bisutils.pbo

import com.flipperplz.bisutils.pbo.entries.BisPboEntry
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import java.io.File
import java.io.RandomAccessFile

class BisPboFile private constructor() : AutoCloseable {
    private lateinit var entries: List<BisPboEntry<*>>
        private set;

    private val propertyCache: Cache<BisPboEntry.BisPboVersionEntry, ByteArray> = CacheBuilder.newBuilder().build<BisPboEntry.BisPboVersionEntry, ByteArray>()
    private val dataCache: Cache<BisPboEntry.BisPboDataEntry, ByteArray> = CacheBuilder.newBuilder().build<BisPboEntry.BisPboDataEntry, ByteArray>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getCachedEntry(entry: BisPboEntry<T>): T? = when(entry) {
        is BisPboEntry.BisPboDataEntry -> dataCache.getIfPresent(entry) as T
        is BisPboEntry.BisPboVersionEntry -> propertyCache.getIfPresent(entry) as T
        else -> null
    }

    fun <T> getOrCreateCachedEntry(entry: BisPboEntry<T>, file: File? = null, managePbo: Boolean = true): T? {
         return getCachedEntry(entry) ?: entry.readBlock(with(BisPboManager.getRandomAccessFile(this)) {
             return@with (this ?: RandomAccessFile(file ?: return null, "r")).also { createdFile ->
                if(managePbo && this == null) BisPboManager.managePbo(this@BisPboFile, createdFile)
             }
         },false);
    }


    companion object {

        fun parse(file: File, keepManaged: Boolean, keepRaw: Boolean): BisPboFile =  with(BisPboFile()) {
            val randomAccessFile = RandomAccessFile(file, "r")
            val entryList = mutableListOf<BisPboEntry<*>>()
            if(keepManaged) BisPboManager.managePbo(this, randomAccessFile)

            var currentEntry: BisPboEntry<*>?

            do {
                currentEntry = BisPboEntry.parse(this, randomAccessFile, keepRaw)
                currentEntry?.let { entryList.add(it) }
            } while (currentEntry !is BisPboEntry.BisPboDummyEntry)

            for (entry in entries) {
                if(entry !is BisPboEntry.BisPboDataEntry) continue
                entry.readBlock(randomAccessFile, keepRaw)
            }
            entries.forEach { it.initializeBlock(randomAccessFile) }

            this
        }

    }

    override fun close() {
        dataCache.cleanUp()
        propertyCache.cleanUp()
    }
}
