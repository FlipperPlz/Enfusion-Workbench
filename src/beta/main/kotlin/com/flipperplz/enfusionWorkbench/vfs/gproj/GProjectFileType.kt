package com.flipperplz.enfusionWorkbench.vfs.gproj

import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.psi.languages.gproj.GProjLanguage
import com.intellij.icons.AllIcons
import javax.swing.Icon

class GProjectFileType : EnfusionLanguageFileType(GProjLanguage) {
    override fun getName(): String = "Project Config"

    override fun getDescription(): String = "Project config for Bohemia's Enfusion Workbench"

    override fun getDefaultExtension(): String = "gproj"

    override fun getIcon(): Icon = AllIcons.FileTypes.Config
}