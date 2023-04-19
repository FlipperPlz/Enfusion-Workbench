package com.flipperplz.enfusionWorkbench.languages.pbo.vfs

import com.intellij.openapi.util.io.FileSystemUtil
import com.intellij.openapi.vfs.impl.ArchiveHandler
import com.intellij.util.io.FileAccessorCache

class PboHandler(path: String) : ArchiveHandler(path) {

    @Volatile
    var myFileStamp: Long = DEFAULT_TIMESTAMP

    @Volatile
    var myFileLength: Long = DEFAULT_LENGTH

    val accessorCache: FileAccessorCache<PboHandler, PboArchiveHolder> = cache;

    override fun createEntriesMap(): MutableMap<String, EntryInfo> {
        TODO("Not yet implemented")
    }

    override fun contentsToByteArray(relativePath: String): ByteArray {
        TODO("Not yet implemented")
    }

    companion object  {

        val cache = object : FileAccessorCache<PboHandler, PboArchiveHolder>(10, 20) {

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
