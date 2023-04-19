package com.flipperplz.enfusionWorkbench.vfs.pbo.impl

import com.flipperplz.enfusionWorkbench.utils.EnfLoggableObject
import com.intellij.openapi.vfs.impl.ArchiveHandler
import com.intellij.util.io.FileAccessorCache
import com.intellij.util.io.ResourceHandle
import java.io.IOException
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes

class PboHandler(path: String) : ArchiveHandler(path) {
    @Volatile
    var myFileStamp: Long = DEFAULT_TIMESTAMP

    @Volatile
    var myFileLength: Long = DEFAULT_LENGTH

    @Throws(IOException::class)
    fun acquirePboHandle(): ResourceHandle<PboArchiveHolder> {
        return try {
            var handle = PboHandler.fileAccessorCache.get(this)
            val attrs = Files.readAttributes(
                file.toPath(),
                BasicFileAttributes::class.java
            )
            if (attrs.lastModifiedTime().toMillis() != myFileStamp || attrs.size() != myFileLength) {
                clearCaches()
                handle.release()
                handle = PboHandler.fileAccessorCache.get(this)
            }
            handle
        } catch (e: RuntimeException) {
            val cause = e.cause
            if (cause is IOException) throw (cause as IOException?)!!
            throw e
        }
    }

    @Throws(IOException::class)
    override fun createEntriesMap(): MutableMap<String, EntryInfo> = acquirePboHandle().use { zipRef ->
        return buildEntryMapForPboFile(zipRef.get())
    }

    @Throws(IOException::class)
    override fun contentsToByteArray(relativePath: String): ByteArray {
        LOG.error("Called contentsToByteArray with relativePath \"${relativePath}\"")
        return try {
            val pboRef = acquirePboHandle()
            val pbo = pboRef.get()

            val v = pbo.contentsFromName(normalizeName(relativePath), false) ?: byteArrayOf()
            v
        } catch (e: RuntimeException) {
            val cause = e.cause
            if (cause is IOException) throw (cause as IOException?)!!
            throw e
        }
    }

    @Suppress("OverrideOnly")
    override fun clearCaches() {
        fileAccessorCache.remove(this)
        super.clearCaches()
    }

    private fun buildEntryMapForPboFile(holder: PboArchiveHolder): MutableMap<String, EntryInfo> {
        val map: MutableMap<String, EntryInfo> = mutableMapOf()

        for(entry in holder) {
            processEntry(map, LOG, entry.fileName) { parent, name ->
                EntryInfo(name, false, entry.blockLength, DEFAULT_TIMESTAMP, parent)
            }
        }

        return map
    }

    companion object : EnfLoggableObject(PboHandler::class.java) {
        private val fileAccessorCache = object : FileAccessorCache<PboHandler, PboArchiveHolder>(20, 10) {
            @Throws(IOException::class)
            override fun createAccessor(handler: PboHandler): PboArchiveHolder {
                val file = handler.file
                val attrs = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
                handler.myFileStamp = attrs.lastModifiedTime().toMillis()
                handler.myFileLength = attrs.size()
                return PboArchiveHolder(file)
            }

            @Throws(IOException::class)
            override fun disposeAccessor(fileAccessor: PboArchiveHolder) {
                fileAccessor.close()
            }

            override fun isEqual(val1: PboHandler?, val2: PboHandler?): Boolean {
                return val1 == val2
            }
        }

        fun clearFileAccessorCache() = fileAccessorCache.clear()

    }

}
