package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.utils.ParamCommandTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamDeleteStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStatementImpl
import com.intellij.lang.ASTNode

abstract class ParamDeleteStatementMixin(node: ASTNode) : ParamStatementImpl(node), ParamDeleteStatement {
    override val slimDeleteTarget: String?
        get() = identifier?.name

    override fun toParam(): String = super<ParamDeleteStatement>.toParam()

    override val statementId: Byte = super<ParamDeleteStatement>.statementId
    override val slimCommandType: ParamCommandTypes = super<ParamDeleteStatement>.slimCommandType

}