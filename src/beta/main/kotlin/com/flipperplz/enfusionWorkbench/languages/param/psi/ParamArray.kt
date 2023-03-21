package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamLiteralType


interface ParamArray : ParamArrayElement, Iterable<ParamArrayElement> {
    override val valueType: ParamLiteralType
        get() = ParamLiteralType.ParamArray
    val arrayElements: List<ParamArrayElement>

    override fun asParsableText(): String = arrayElements.asParamArrayString()
}
