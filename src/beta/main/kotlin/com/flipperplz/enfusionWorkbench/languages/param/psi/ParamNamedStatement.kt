package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamNamedStatement : ParamStatement, ParamNamedElement {
    override val binarizable: Boolean
        get() = true
}