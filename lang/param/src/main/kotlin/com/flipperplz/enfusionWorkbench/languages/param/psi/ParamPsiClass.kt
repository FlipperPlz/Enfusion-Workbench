package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamPsiClass : ParamPsiExternalClass, ParamPsiCommandsHolder {
    val paramSuperClass: ParamIdentifier?
}