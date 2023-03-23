package com.flipperplz.enfusionWorkbench.psi

import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement

interface EnfusionNamedPsiElement : EnfusionPsiElement, PsiNamedElement, PsiNameIdentifierOwner