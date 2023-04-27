package com.flipperplz.bisutils.languages.param.psi

interface ParamPsiExternalClass : ParamStatement, ParamPsiNamedElement {
    val className: String?
    val isExternalParamClass: Boolean
        get() = true
}