package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamLiteralType

interface ParamString : ParamLiteral {
    override val valueType: ParamLiteralType
        get() = ParamLiteralType.ParamString
    var quoted: Boolean
}