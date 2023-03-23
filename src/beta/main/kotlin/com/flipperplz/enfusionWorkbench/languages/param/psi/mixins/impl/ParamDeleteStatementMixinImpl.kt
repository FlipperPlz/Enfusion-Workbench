package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamDeleteStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNamedPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamDeleteStatementMixinImpl(
    node: ASTNode
) : ParamPsiElementImpl(
    node
), ParamDeleteStatement, ParamNamedPsiElement {

    override fun getNameIdentifier(): PsiElement? = identifier

    override fun getName(): String? = identifier?.name

    override fun setName(name: String): PsiElement? = identifier?.setName(name)
}