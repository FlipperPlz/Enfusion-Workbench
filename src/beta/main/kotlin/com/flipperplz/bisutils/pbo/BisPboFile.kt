package com.flipperplz.bisutils.pbo

import com.flipperplz.bisutils.BisPboManager
import com.flipperplz.bisutils.utils.BisRandomAccessFile
import com.google.common.cache.CacheBuilder
import java.io.File

class BisPboFile private constructor() : AutoCloseable {
    lateinit var entries: List<BisPboEntry<*>>
        private set;

    private val dataCache = CacheBuilder.newBuilder().build<BisPboEntry.BisPboDataEntry, ByteArray>()
    private val pboProperties
        get() = entries.filterIsInstance<BisPboEntry.BisPboVersionEntry>().flatMap { it.properties }

    val pboPrefix: String?
        get() = pboProperties.firstOrNull { it.propertyName == "prefix" }?.propertyValue
            ?: if (BisPboManager.isManaged(this)) null else BisPboManager.getRandomAccessFile(this)?.file?.nameWithoutExtension


    val pboProduct: String?
        get() = pboProperties.firstOrNull { it.propertyName == "product" }?.propertyValue

    val pboVersion: String?
        get() = pboProperties.firstOrNull { it.propertyName == "version" }?.propertyValue

    @Suppress("UNCHECKED_CAST")
    private fun  getCachedEntry(entry: BisPboEntry.BisPboDataEntry): ByteArray? = dataCache.getIfPresent(entry);

    fun getOrCreateCachedEntry(entry: BisPboEntry.BisPboDataEntry, file: File? = null, managePbo: Boolean = true): ByteArray? {
        return getCachedEntry(entry) ?: entry.readBlock(with(BisPboManager.getRandomAccessFile(this)) {
            return@with (this ?: BisRandomAccessFile(file ?: return null, "r")).also { createdFile ->
                if(managePbo && this == null) BisPboManager.managePbo(this@BisPboFile, createdFile)
            }
        },false).also { dataCache.put(entry as BisPboEntry.BisPboDataEntry, it) };
    }

    fun getInfo(): String {
        val sep = "\t" + "-".repeat(120)
        return "entryCount = ${entries.count()},\n" +
                "entries = [\n" + entries.joinToString(separator = "\n\n$sep\n\n\t") { it.getInfo(1)} +
                "\n]"
    }


    companion object {

        fun parse(file: File, keepManaged: Boolean, keepRaw: Boolean): BisPboFile =  with(BisPboFile()) {
            val randomAccessFile = BisRandomAccessFile(file, "r")
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
        BisPboManager.releasePbo(this)
    }
}
