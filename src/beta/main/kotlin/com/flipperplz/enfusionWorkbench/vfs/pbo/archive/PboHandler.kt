package com.flipperplz.enfusionWorkbench.vfs.pbo.archive

import com.intellij.openapi.util.io.FileSystemUtil
import com.intellij.openapi.vfs.impl.ArchiveHandler
import com.intellij.util.io.FileAccessorCache

class PboHandler(path: String) : ArchiveHandler(path) {

    @Volatile
    var myFileStamp: Long = DEFAULT_TIMESTAMP

    @Volatile
    var myFileLength: Long = DEFAULT_LENGTH


    val archiveHolder: PboArchiveHolder
        get() = accessorCache.get(this).get()


    override fun createEntriesMap(): MutableMap<String, EntryInfo> {
        val map = mutableMapOf<String, EntryInfo>()
        val root = createRootEntry()
        map[archiveHolder.archive.pboPrefix!!] = root
        archiveHolder.entries.forEach {
            var name = it.fileName
            var count = 1
            while (map.containsKey(name)) {
                name = "${it.fileName.substringBeforeLast(".")}$count.${it.fileName.substringAfterLast(".")}"
                count++
            }
            map[name] = EntryInfo(name, false, it.blockLength, myFileStamp, root)
        }
        return map
    }

    override fun contentsToByteArray(relativePath: String): ByteArray {

        return byteArrayOf() //TODO
    }

    companion object  {

        val accessorCache = object : FileAccessorCache<PboHandler, PboArchiveHolder>(10, 20) {

            override fun createAccessor(key: PboHandler): PboArchiveHolder {
                val attributes = FileSystemUtil.getAttributes(key.file.canonicalFile)
                key.myFileStamp = attributes?.lastModified ?: DEFAULT_TIMESTAMP
                key.myFileLength = attributes?.length ?: DEFAULT_LENGTH
                return PboArchiveHolder(key.file.canonicalFile)
            }

            override fun disposeAccessor(fileAccessor: PboArchiveHolder) {
                fileAccessor.close()
            }
        }
    }
}
