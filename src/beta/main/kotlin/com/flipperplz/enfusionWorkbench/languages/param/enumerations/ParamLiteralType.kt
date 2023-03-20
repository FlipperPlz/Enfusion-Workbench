package com.flipperplz.enfusionWorkbench.languages.param.enumerations

import kotlin.reflect.KType
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.typeOf

enum class ParamLiteralType(
    val convertsTo: KType
) {
    ParamString(typeOf<String>()),

    ParamInt(typeOf<Int>()),
    ParamFloat(typeOf<Float>()),
    ParamDouble(typeOf<Double>()),

    ParamArray(typeOf<com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArray>()),
    ParamUnknown(typeOf<Any>());
    companion object {
        fun typeFromNumber(num: Number): ParamLiteralType = values().first() { it.convertsTo == num::class.starProjectedType }
    }
}