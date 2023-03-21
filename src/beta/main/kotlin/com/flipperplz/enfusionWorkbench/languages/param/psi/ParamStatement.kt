package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamStatement : ParamElement {
    val parentScope: ParamScope

    fun asParsableText(): String
}