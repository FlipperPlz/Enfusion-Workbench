package com.flipperplz.enfusionWorkbench.vfs.enforce

import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguageFileType
import com.intellij.icons.AllIcons
import javax.swing.Icon

class EnforceFileType : EnfusionLanguageFileType(com.flipperplz.enfusionWorkbench.psi.languages.enforce.EnforceLanguage) {
    override fun getName(): String = "Enforce Script"

    override fun getDescription(): String = "Enforce script file"

    override fun getDefaultExtension(): String = "c"

    override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript
}