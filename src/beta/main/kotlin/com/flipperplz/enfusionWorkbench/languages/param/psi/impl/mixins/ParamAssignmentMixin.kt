package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamAssignment
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType

abstract class ParamAssignmentMixin(node: ASTNode) : ParamStatementMixin(node), ParamAssignment {
    override val paramAssignee: ParamIdentifier
        get() = childrenOfType<ParamIdentifier>().first()

    override val paramValue: ParamArrayElement
        get() =  childrenOfType<ParamArrayElement>().first()

    override fun getName(): String? = paramAssignee.name
    override fun setName(name: String): PsiElement = paramAssignee.setName(name)
}