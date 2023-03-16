package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

interface ParamNumericLiteralContext {
    fun asKotlinNumber(): Number
    fun asKotlinString(): String
}