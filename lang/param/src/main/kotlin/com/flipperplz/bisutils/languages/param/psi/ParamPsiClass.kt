package com.flipperplz.bisutils.languages.param.psi

interface ParamPsiClass : ParamPsiExternalClass, ParamPsiCommandsHolder {
    val paramSuperClass: ParamIdentifier?
}