package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamPsiCommandsHolder {
    val commands: List<ParamCommand>
    val parentCommandsHolder: ParamPsiCommandsHolder?
}