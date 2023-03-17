package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.GeneratedParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamAssignment
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

abstract class ParamAssignmentMixin(node: ASTNode) : ParamStatementMixin(node), ParamAssignment {
    override fun getAssignee(): ParamIdentifier = childrenOfType<ParamIdentifier>().first()
    override fun getValue(): GeneratedParamArrayElement = childrenOfType<GeneratedParamArrayElement>().first()
    override fun getName(): String? = getAssignee().name
}