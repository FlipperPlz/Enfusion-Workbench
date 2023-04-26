package com.flipperplz.bisutils.languages.param.psi

interface ParamPsiClassBase : ParamStatement, ParamPsiNamedElement {
    val className: String?
        get() = name
    val isExternalParamClass: Boolean
        get() = this is ParamExternalClassStatement
}