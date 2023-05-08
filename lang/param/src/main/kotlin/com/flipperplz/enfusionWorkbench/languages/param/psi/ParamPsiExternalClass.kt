package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.bisutils.param.slim.ParamSlimExternalClass

interface ParamPsiExternalClass : ParamPsiNamedElement, ParamSlimExternalClass {
    val isExternalParamClass: Boolean
        get() = true
}