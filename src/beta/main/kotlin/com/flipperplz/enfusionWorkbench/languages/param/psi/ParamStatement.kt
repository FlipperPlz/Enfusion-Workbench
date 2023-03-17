package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamStatement : ParamElement {
    fun getParentScope(): ParamScope
}