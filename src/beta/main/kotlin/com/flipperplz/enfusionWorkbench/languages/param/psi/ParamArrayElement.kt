package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamLiteralType

interface ParamArrayElement : ParamElement {
    val valueType: ParamLiteralType
}