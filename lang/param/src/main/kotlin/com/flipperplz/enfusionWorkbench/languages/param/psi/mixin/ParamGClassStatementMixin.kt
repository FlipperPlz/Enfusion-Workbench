package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.ParamStatement
import com.flipperplz.bisutils.param.statement.ParamExternalClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGExternalClassStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamGClassStatementMixin(node: ASTNode) : ParamGExternalClassStatementImpl(node), ParamGClassStatement {
    override fun shouldValidateSuper(): Boolean = false

    override fun locateSuperClass(): ParamExternalClass? {
        TODO("Not yet implemented")
    }

    override val slimSuperClass: String?
        get() = identifierList.getOrNull(1)?.name
    override val slimCommands: List<ParamStatement>
        get() = this.statementList
}