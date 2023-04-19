package com.flipperplz.bisutils.pbo

import com.flipperplz.bisutils.pbo.misc.BisPboProperty
import com.google.common.cache.CacheBuilder
import java.io.File
import java.io.RandomAccessFile

class BisPboFile private constructor() : AutoCloseable {
    lateinit var entries: List<BisPboEntry<*>>
        private set;

    private val propertyCache = CacheBuilder.newBuilder().build<BisPboEntry.BisPboVersionEntry, List<BisPboProperty>>()
    private val dataCache = CacheBuilder.newBuilder().build<BisPboEntry.BisPboDataEntry, ByteArray>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getCachedEntry(entry: BisPboEntry<T>): T? = when(entry) {
        is BisPboEntry.BisPboDataEntry -> dataCache.getIfPresent(entry) as? T
        is BisPboEntry.BisPboVersionEntry -> propertyCache.getIfPresent(entry) as? T
        else -> null
    }

    fun <T> getOrCreateCachedEntry(entry: BisPboEntry<T>, file: File? = null, managePbo: Boolean = true): T? {
        return getCachedEntry(entry) ?: entry.readBlock(with(BisPboManager.getRandomAccessFile(this)) {
            return@with (this ?: RandomAccessFile(file ?: return null, "r")).also { createdFile ->
                if(managePbo && this == null) BisPboManager.managePbo(this@BisPboFile, createdFile)
            }
        },false).also {
            when(it) {
                is ByteArray -> dataCache.put(entry as BisPboEntry.BisPboDataEntry, it)
                is List<*> -> propertyCache.put(entry as BisPboEntry.BisPboVersionEntry, it.filterIsInstance<BisPboProperty>())
                else -> throw Exception("Can not cache $it")
            }
        };
    }

    fun getInfo(): String {
        val sep = "\t" + "-".repeat(120)
        return "entryCount = ${entries.count()},\n" +
                "entries = [\n" + entries.joinToString(separator = "\n\n$sep\n\n\t") { it.getInfo(1)} +
                "\n]"
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
            entries = entryList

            for (entry in entries) {
                if(entry !is BisPboEntry.BisPboDataEntry) continue
                entry.initializeBlock(randomAccessFile)
            }

            this
        }

    }

    override fun close() {
        dataCache.cleanUp()
        propertyCache.cleanUp()
    }
}
