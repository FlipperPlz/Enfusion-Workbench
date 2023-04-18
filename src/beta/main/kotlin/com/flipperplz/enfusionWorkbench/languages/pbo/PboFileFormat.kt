package com.flipperplz.enfusionWorkbench.languages.pbo

import com.intellij.ide.highlighter.ArchiveFileType

class PboFileFormat : ArchiveFileType() {
    companion object {
        val instance: PboFileFormat = PboFileFormat();
    }

    override fun getName(): String = "PBO"
    override fun getDescription(): String = "An archive format used in older bohemia games."
    override fun getDefaultExtension(): String = "pbo"
}