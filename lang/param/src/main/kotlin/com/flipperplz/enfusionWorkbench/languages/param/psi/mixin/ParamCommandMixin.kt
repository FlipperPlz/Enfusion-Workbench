package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.utils.ParamCommandTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamCommand
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ParamCommandMixin(node: ASTNode) : ASTWrapperPsiElement(node), ParamCommand {
    override fun toParam(): String = command?.toParam() ?: ""
    override val statementId: Byte
        get() = command?.statementId ?: throw Exception()
    override val slimCommandType: ParamCommandTypes
        get() = command?.slimCommandType ?: throw Exception()
    override val slimParent: RapElement?
        get() = parent as? RapElement
}