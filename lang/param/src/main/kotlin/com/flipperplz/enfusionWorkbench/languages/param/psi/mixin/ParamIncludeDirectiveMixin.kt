package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.utils.ParamLiteralTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIncludeDirective
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPureDirectiveImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamIncludeDirectiveMixin(node: ASTNode) : ParamPureDirectiveImpl(node), ParamIncludeDirective {
    override fun toParam(): String {
        TODO("Not yet implemented")
    }

    override val slimLiteralType: ParamLiteralTypes
        get() = TODO("Not yet implemented")
    override val slimValue: Any?
        get() = TODO("Not yet implemented")
    override val literalId: Byte?
        get() = TODO("Not yet implemented")
    override val slimParent: RapElement?
        get() = TODO("Not yet implemented")

}