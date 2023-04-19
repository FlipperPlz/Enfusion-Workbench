package com.flipperplz.enfusionWorkbench.vfs.pbo.archive

import com.flipperplz.bisutils.BisPboManager
import com.flipperplz.bisutils.pbo.BisPboEntry
import com.flipperplz.bisutils.pbo.BisPboFile
import java.io.Closeable
import java.io.File

class PboArchiveHolder(file: File) : Closeable {
    val archive: BisPboFile = BisPboFile.parse(file, keepManaged = true, keepRaw = true)
    val entries: List<BisPboEntry.BisPboDataEntry> by lazy {
        archive.entries.filterIsInstance<BisPboEntry.BisPboDataEntry>()
    }
    val reader = BisPboManager.getRandomAccessFile(archive)


    fun entryFromName(name: String, caseSensitive: Boolean = false): BisPboEntry.BisPboDataEntry? {
        with(if (caseSensitive) name else name.lowercase()) {
            return entries.firstOrNull { it.fileName == this }
        }
    }

    fun contentsFromName(name: String, caseSensitive: Boolean = false): ByteArray? = entryFromName(name, caseSensitive)?.cachedBlock

    override fun close() {
        archive.close()
    }
}