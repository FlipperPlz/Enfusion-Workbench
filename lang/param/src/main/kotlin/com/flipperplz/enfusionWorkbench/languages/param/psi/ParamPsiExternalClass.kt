package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamPsiExternalClass : ParamStatement, ParamPsiNamedElement {
    val className: String?
    val isExternalParamClass: Boolean
        get() = true
}