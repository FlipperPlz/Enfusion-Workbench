package com.flipperplz.enfusionWorkbench.languages.param.psi


interface ParamNumeric : ParamLiteral {
    fun asKtNumber(): Number

    override fun asParsableText(): String = asKtNumber().toString()
}
