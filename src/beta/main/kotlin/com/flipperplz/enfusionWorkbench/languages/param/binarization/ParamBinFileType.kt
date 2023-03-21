package com.flipperplz.enfusionWorkbench.languages.param.binarization

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

object ParamBinFileType : FileType {
    override fun getName(): String = "ParamC"

    override fun getDescription(): String = "Compiled Param File";

    override fun getDefaultExtension(): String = "bin"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config

    override fun isBinary(): Boolean = true

    override fun getCharset(file: VirtualFile, content: ByteArray?): String? = Charsets.UTF_8.name()
}