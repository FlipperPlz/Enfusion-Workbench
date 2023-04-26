package com.flipperplz.bisutils.languages.param.psi

interface ParamPsiClassBase : ParamStatement, ParamPsiNamedElement {
    val className: String?
    val isExternalParamClass: Boolean
}