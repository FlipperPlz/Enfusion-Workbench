package com.flipperplz.enfusionWorkbench.vfs.param

import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.intellij.icons.AllIcons
import javax.swing.Icon

class ParamFileType : EnfusionLanguageFileType(ParamLanguage) {
    companion object {
        val instance: ParamFileType = ParamFileType();
    }

    override fun getName(): String = "Param"
    override fun getDescription(): String = "A type of configuration file used by Bohemias RV/Enforce engine (Pre-Enfusion)"
    override fun getDefaultExtension(): String = "cpp"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config

}