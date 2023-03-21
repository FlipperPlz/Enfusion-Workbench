package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamElement : PsiElement {
    val binarizable: Boolean get() = false
}