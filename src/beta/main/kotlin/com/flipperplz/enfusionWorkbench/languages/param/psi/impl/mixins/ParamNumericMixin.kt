package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumeric
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamNumericMixin(node: ASTNode) : ParamLiteralImpl(node), ParamNumeric {
    override fun asKtNumber(): Number {
        TODO("Not yet implemented")
    }

    override fun asKtString(): String = text
}