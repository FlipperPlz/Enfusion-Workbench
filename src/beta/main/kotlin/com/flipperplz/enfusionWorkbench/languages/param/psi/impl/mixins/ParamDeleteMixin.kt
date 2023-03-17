package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamDelete
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

abstract class ParamDeleteMixin(node: ASTNode) : ParamStatementMixin(node), ParamDelete {
    override val paramDeleting: ParamIdentifier
        get() = childrenOfType<ParamIdentifier>().first()
}