package com.flipperplz.enfusionWorkbench.languages.param

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object ParamFileType : LanguageFileType(ParamLanguage){
    override fun getName(): String = "Param File"
    override fun getDescription(): String = "A type of configuration file used by Bohemias RV/Enforce engine (Pre-Enfusion)"
    override fun getDefaultExtension(): String = "cpp"
    override fun getIcon(): Icon = AllIcons.FileTypes.Config
}