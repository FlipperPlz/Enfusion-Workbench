package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamParameterStatement
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamIdentifierImpl
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

abstract class ParamParameterStatementMixin(
    node: ASTNode
) : ParamParameterStatement, PsiNameIdentifierOwner, ParamStatementImpl(node) {

    override fun getNameIdentifier(): ParamIdentifierImpl = parameterName as ParamIdentifierImpl

    override fun getName(): String? = nameIdentifier.name

    override fun setName(name: String): PsiElement = nameIdentifier.setName(name)
}