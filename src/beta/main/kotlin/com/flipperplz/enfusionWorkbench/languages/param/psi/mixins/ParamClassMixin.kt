package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.interfaces.ParamPsiStatementScope
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

abstract class ParamClassMixin(node: ASTNode) : ParamStatementImpl(node), PsiNameIdentifierOwner, ParamClassStatement, ParamPsiStatementScope {
    override val isExternal: Boolean = symLcurly == null
    override val statements: List<ParamStatement> = statementList
    override val paramElementName: String
        get() = if (parentName?.name != null) {
            "${classname?.name ?: "unidentified"}#${parentName?.name ?: "unidentified"}"
        } else classname?.name ?: "unidentified"

    override fun setName(p0: String): PsiElement? = classname?.setName(p0)

    override fun getName(): String? = classname?.name

    override fun getNameIdentifier(): PsiElement? = classname
}