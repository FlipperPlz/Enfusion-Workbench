package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamIdentifier : ParamNamedElement {
    override val binarizable: Boolean
        get() = true

}