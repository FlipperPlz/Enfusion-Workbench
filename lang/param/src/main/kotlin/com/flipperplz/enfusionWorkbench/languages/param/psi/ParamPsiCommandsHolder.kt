package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamPsiCommandsHolder : PsiElement {
    val commands: List<ParamCommand>
    val parentCommandsHolder: ParamPsiCommandsHolder?
}