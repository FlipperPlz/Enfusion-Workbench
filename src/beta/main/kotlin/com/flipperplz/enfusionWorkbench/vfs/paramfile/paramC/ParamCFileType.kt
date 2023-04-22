package com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ParamCFileType :  LanguageFileType(ParamLanguage, true) {
    companion object {
        val instance = ParamCFileType()
    }

    override fun getName(): String = "Binarized ParamFile"

    override fun getDescription(): String = "A binarized ParamFile"

    override fun getDefaultExtension(): String = "bin"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config

    override fun isReadOnly(): Boolean = true
}