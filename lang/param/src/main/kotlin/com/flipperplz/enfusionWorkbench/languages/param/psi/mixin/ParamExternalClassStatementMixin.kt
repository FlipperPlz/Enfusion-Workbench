package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamExternalClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamExternalClassStatementMixin(node: ASTNode) : ParamStatementImpl(node), ParamExternalClassStatement {
    override val slimParent: RapElement?
        get() = parent as? RapElement
    override val slimClassName: String?
        get() = identifier?.name
}