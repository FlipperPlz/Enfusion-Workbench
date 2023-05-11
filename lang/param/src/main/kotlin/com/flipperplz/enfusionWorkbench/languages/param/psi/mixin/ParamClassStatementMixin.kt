package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapStatement
import com.flipperplz.bisutils.param.utils.ParamCommandTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamCommand
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamExternalClassStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamClassStatementMixin(node: ASTNode) : ParamExternalClassStatementImpl(node), ParamClassStatement {
    override fun toParam(): String = super<ParamClassStatement>.toParam()

    override val statementId: Byte
        get() = super<ParamClassStatement>.statementId
    override val slimCommandType: ParamCommandTypes
        get() = super<ParamClassStatement>.slimCommandType
    override val slimSuperClass: String?
        get() = identifierList[1]?.name
    override val slimCommands: List<RapStatement>
        get() = commandList


}