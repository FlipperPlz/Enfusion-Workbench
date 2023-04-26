package com.flipperplz.bisutils.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamPsiCommandsHolder : PsiElement {
    val commands: List<ParamCommand>
}