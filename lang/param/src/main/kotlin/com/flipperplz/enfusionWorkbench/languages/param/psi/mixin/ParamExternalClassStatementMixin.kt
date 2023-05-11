package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.utils.ParamCommandTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamExternalClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamExternalClassStatementMixin(node: ASTNode) : ParamStatementImpl(node), ParamExternalClassStatement {
    override fun toParam(): String = super<ParamExternalClassStatement>.toParam()


    override val statementId: Byte
        get() = super<ParamExternalClassStatement>.statementId

    override val slimParent: RapElement?
        get() = parent as? RapElement
    override val slimCommandType: ParamCommandTypes
        get() = super<ParamExternalClassStatement>.slimCommandType
    override val slimClassName: String?
        get() = identifier?.name
}