package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.ParamStatement
import com.flipperplz.bisutils.param.node.ParamStatementHolder
import com.flipperplz.bisutils.param.statement.ParamExternalClass
import com.flipperplz.bisutils.param.utils.extensions.ParamSlimUtils.findElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGExternalClassStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamGClassStatementMixin(node: ASTNode) : ParamGExternalClassStatementImpl(node), ParamGClassStatement {
    override fun shouldValidateSuper(): Boolean = false

    override fun locateSuperClass(): ParamExternalClass? = if(!slimSuperClass.isNullOrBlank() && containingParamFile != null)
        containingParamFile?.findElement(slimSuperClass!!) as ParamExternalClass
    else null

    override val slimSuperClass: String?
        get() = identifierList.getOrNull(1)?.name
    override val slimCommands: List<ParamStatement>
        get() = this.statementList
}