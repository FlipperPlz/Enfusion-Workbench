package com.flipperplz.enfusionWorkbench.formats.pbo

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.intellij.icons.AllIcons
import com.intellij.ide.highlighter.ArchiveFileType
import javax.swing.Icon

class PboFileFormat : ArchiveFileType() {
    companion object {
        val instance: PboFileFormat = PboFileFormat();
    }

    override fun getName(): String = "PBO"
    override fun getDescription(): String = "An archive format used in older bohemia games."
    override fun getDefaultExtension(): String = "pbo"
}