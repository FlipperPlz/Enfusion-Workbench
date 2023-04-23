package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamStatement
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins.ParamNonBinaraizableMixin
import com.flipperplz.enfusionWorkbench.vfs.paramfile.param.ParamFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType

interface ParamFile : PsiFile, ParamPsiStatementScope {
    val isBinarizedFile: Boolean

    override fun getNameIdentifier(): PsiElement? = null

    override val statements: List<ParamStatement>
        get() = childrenOfType<ParamStatement>()

    override val binarizable: Boolean
        get() = children.any { it is ParamNonBinaraizableMixin }

    override val isExternal: Boolean
        get() = false

    override fun getFileType(): FileType = if (isBinarizedFile) ParamCFileType.instance else ParamFileType.instance

}