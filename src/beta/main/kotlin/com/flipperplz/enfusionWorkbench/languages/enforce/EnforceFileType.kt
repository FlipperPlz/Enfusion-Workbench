package com.flipperplz.enfusionWorkbench.languages.enforce

import com.flipperplz.enfusionWorkbench.languages.EnfusionLanguageFileType
import com.intellij.icons.AllIcons
import javax.swing.Icon

class EnforceFileType : EnfusionLanguageFileType(EnforceLanguage) {
    override fun getName(): String = "Enforce Script"

    override fun getDescription(): String = "Enforce script file"

    override fun getDefaultExtension(): String = "c"

    override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript
}