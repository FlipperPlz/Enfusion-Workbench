package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.ParamLiteralType
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamLiteral
import com.intellij.lang.ASTNode

open class ParamLiteralImpl(node: ASTNode) : ParamElementImpl(node), ParamLiteral {
    override fun asKtString(): String = "__Dummy__Literal__"
    override val valueType: ParamLiteralType
        get() = ParamLiteralType.ParamUnknown
}