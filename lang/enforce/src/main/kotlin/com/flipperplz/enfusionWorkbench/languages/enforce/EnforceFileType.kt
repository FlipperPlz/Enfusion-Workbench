package com.flipperplz.enfusionWorkbench.languages.enforce

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class EnforceFileType : LanguageFileType(EnforceLanguage) {
    companion object {
        val instance = EnforceFileType()
    }

    override fun getName(): String = "Enforce"

    override fun getDescription(): String = "A type of script file used by Bohemias Enfusion Engine"

    override fun getDefaultExtension(): String = "cpp"

    //TODO(ryann): Enforce FileType Icon
    override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript
}