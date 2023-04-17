package com.flipperplz.enfusionWorkbench.formats.pbo.vfs

import com.intellij.openapi.vfs.impl.ArchiveHandler

class PboHandler(path: String) : ArchiveHandler(path) {
    override fun createEntriesMap(): MutableMap<String, EntryInfo> {
        TODO("Not yet implemented")
    }

    override fun contentsToByteArray(relativePath: String): ByteArray {
        TODO("Not yet implemented")
    }

}
