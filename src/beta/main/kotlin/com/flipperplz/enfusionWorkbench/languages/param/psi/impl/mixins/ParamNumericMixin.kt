package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamLiteralType
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumeric
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamNumericMixin(node: ASTNode) : ParamLiteralImpl(node), ParamNumeric {
    override fun asKtNumber(): Number = text.toDoubleOrNull()?.let {
        when {
            it.isFinite() && it.toLong().toDouble() == it -> it.toLong()
            it.isFinite() && it.toFloat().toDouble() == it -> it.toFloat()
            else -> it
        }
    } ?: throw NumberFormatException("Invalid number format: ${this.text}")

    override val valueType: ParamLiteralType
        get() = ParamLiteralType.typeFromNumber(asKtNumber())

    override fun asKtString(): String = text
}