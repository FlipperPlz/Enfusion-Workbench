package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamExternalClassStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamClassStatementMixin(node: ASTNode) : ParamExternalClassStatementImpl(node), ParamClassStatement {
    override val slimSuperClass: String?
        get() = identifierList[1]?.name
    override val slimCommands: List<RapStatement>
        get() = commandList
}