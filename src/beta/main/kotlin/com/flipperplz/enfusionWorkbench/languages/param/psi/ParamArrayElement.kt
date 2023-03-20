package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamLiteralType

interface ParamArrayElement : ParamElement {
    val valueType: ParamLiteralType
    fun asKtString(): String
}