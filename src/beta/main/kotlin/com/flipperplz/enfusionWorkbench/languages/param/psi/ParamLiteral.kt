package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamLiteral : ParamArrayElement {
    fun asKtString(): String
}