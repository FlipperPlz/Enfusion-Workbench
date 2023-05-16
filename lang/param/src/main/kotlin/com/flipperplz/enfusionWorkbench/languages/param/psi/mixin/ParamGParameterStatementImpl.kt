package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.ParamLiteralBase
import com.flipperplz.bisutils.param.utils.ParamOperatorTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGParameterStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamGParameterStatementImpl(node: ASTNode) : ParamGStatementImpl(node), ParamGParameterStatement{
    override val slimOperator: ParamOperatorTypes?
        get() = if(opAddassign != null) ParamOperatorTypes.ADD_ASSIGN else
            if(opSubassign != null) ParamOperatorTypes.SUB_ASSIGN else
                ParamOperatorTypes.ASSIGN
    override val slimValue: ParamLiteralBase?
        get() = literal
    override val slimName: String?
        get() = identifier.name
}