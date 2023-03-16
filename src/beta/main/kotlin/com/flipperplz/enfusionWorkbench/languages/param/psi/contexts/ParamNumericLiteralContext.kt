package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamLiteral

interface ParamNumericLiteralContext : ParamLiteral {
    fun asKotlinNumber(): Number
    fun asKotlinString(): String
}