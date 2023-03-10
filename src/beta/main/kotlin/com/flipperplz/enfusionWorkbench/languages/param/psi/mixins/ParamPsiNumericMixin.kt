package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode
import kotlin.math.abs

abstract class ParamPsiNumericMixin(node: ASTNode) : ParamLiteral, ParamCompositeElementImpl(node) {
    override val isNumeric: Boolean = true

    fun toFloatOrNull(): Float? = text.toFloatOrNull()
    fun toDoubleOrNull(): Double? = text.toDoubleOrNull()
    fun toIntOrNull(): Int? = text.toIntOrNull()

    fun asNumber(): Number {
        val doubleValue = toDoubleOrNull()
        val floatValue = toFloatOrNull()
        val intValue = toIntOrNull()

        if (doubleValue != null && abs(doubleValue) <= Float.MAX_VALUE) {
            return if (abs(doubleValue) <= Int.MAX_VALUE) {
                doubleValue.toInt()
            } else {
                doubleValue.toFloat()
            }
        }
        throw Exception("Failed to parse param number to datatype")
    }
}