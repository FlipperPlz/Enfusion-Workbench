package com.flipperplz.bisutils.languages.param.psi

interface ParamPsiClass : ParamPsiClassBase, ParamPsiCommandsHolder {
    val paramSuperClass: ParamIdentifier?
}