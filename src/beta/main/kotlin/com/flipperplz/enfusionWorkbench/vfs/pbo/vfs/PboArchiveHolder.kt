package com.flipperplz.enfusionWorkbench.vfs.pbo.vfs

import com.flipperplz.bisutils.BisPboManager
import com.flipperplz.bisutils.pbo.BisPboEntry
import com.flipperplz.bisutils.pbo.BisPboFile
import java.io.Closeable
import java.io.File

class PboArchiveHolder(file: File) : Closeable {
    val archive: BisPboFile = BisPboFile.parse(file, keepManaged = true, keepRaw = true)
    val archiveItems: List<BisPboEntry.BisPboDataEntry> by lazy {
        archive.entries.filterIsInstance<BisPboEntry.BisPboDataEntry>()
    }
    val reader = BisPboManager.getRandomAccessFile(archive)


    override fun close() {
        archive.close()
    }
}