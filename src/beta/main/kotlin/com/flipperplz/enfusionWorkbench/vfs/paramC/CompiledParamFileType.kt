package com.flipperplz.enfusionWorkbench.vfs.paramC

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.FileType
import javax.swing.Icon

class CompiledParamFileType : FileType {
    override fun getName(): String = "Binarized ParamFile"

    override fun getDescription(): String = "A binarized ParamFile"

    override fun getDefaultExtension(): String = "bin"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config

    override fun isBinary(): Boolean = true

    override fun isReadOnly(): Boolean = true

}