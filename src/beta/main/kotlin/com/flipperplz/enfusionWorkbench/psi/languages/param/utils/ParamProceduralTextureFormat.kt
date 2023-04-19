package com.flipperplz.enfusionWorkbench.psi.languages.param.utils

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamIdentifier


enum class ParamProceduralTextureFormat(val text: String) {
    A("a"),
    I("i"),
    AI("ai"),
    RGB("rgb"),
    ARGB("argb");

    companion object {
        fun fromIdentifier(format: ParamIdentifier?): ParamProceduralTextureFormat? = when (format?.text) {
            A.text -> A
            I.text -> I
            AI.text -> AI
            RGB.text -> RGB
            ARGB.text -> ARGB
            else -> null
        }
    }
}