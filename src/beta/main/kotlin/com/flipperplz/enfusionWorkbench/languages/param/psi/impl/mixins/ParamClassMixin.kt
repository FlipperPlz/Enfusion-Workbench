package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType

abstract class ParamClassMixin(
    node: ASTNode
) : ParamStatementMixin(node), ParamClass {
    override val className: ParamIdentifier get() = childrenOfType<ParamIdentifier>().first()
    override val superClass: ParamClass? = getPreviousScope()!! getChildExternalClass childrenOfType<ParamIdentifier>()[1].name

    override fun getStatements(): List<ParamStatement> = childrenOfType()

    override fun getName(): String? = className.name
    override fun setName(name: String): PsiElement = className.setName(name)
}