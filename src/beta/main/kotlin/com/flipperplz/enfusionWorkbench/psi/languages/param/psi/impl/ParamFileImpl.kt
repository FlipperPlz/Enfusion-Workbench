package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.param.ParamFileType
import com.flipperplz.enfusionWorkbench.psi.impl.EnfusionPsiFileImpl
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamStatement
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces.ParamPsiStatementScope
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins.ParamNonBinaraizableMixin
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.childrenOfType

class ParamFileImpl(
    viewProvider: FileViewProvider,
    override val isExternal: Boolean = false
) : EnfusionPsiFileImpl(viewProvider, ParamLanguage), ParamPsiStatementScope {

    override val enfusionFileType: EnfusionLanguageFileType = ParamFileType.instance
    override val statements: List<ParamStatement> get() = childrenOfType<ParamStatement>()
    override val binarizable: Boolean
        get() = children.any { it is ParamNonBinaraizableMixin }

    override fun getNameIdentifier(): PsiElement? = null

    override fun acceptChildren(visitor: PsiElementVisitor) {

    }
}