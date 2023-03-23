package com.flipperplz.enfusionWorkbench.psi

import com.intellij.psi.PsiElement

interface EnfusionPsiElement : PsiElement {
    val binarizable: Boolean
}