package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamScope
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.findParentOfType

abstract class ParamStatementMixin(
    node: ASTNode
): ParamElementImpl(node), ParamStatement {
    override fun getParentScope(): ParamScope = findParentOfType<ParamScope>(false)!!
}