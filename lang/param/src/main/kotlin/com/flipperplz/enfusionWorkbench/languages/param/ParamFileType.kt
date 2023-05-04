package com.flipperplz.enfusionWorkbench.languages.param

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ParamFileType : LanguageFileType(ParamLanguage) {
    companion object {
        val instance = ParamFileType()
    }

    /**
     * Returns the name of the file type. The name must be unique among all file types registered in the system.
     */
    override fun getName(): String = "Param"


    /**
     * Returns the user-readable description of the file type.
     */
    override fun getDescription(): String = "A type of configuration file used by Bohemias RV/Enforce engine (Pre-Enfusion)"


    /**
     * Returns the default extension for files of the type, *not* including the leading '.'.
     */
    override fun getDefaultExtension(): String = "cpp"

    /**
     * Returns the icon used for showing files of the type, or `null` if no icon should be shown.
     */
    override fun getIcon(): Icon = AllIcons.FileTypes.Config
}