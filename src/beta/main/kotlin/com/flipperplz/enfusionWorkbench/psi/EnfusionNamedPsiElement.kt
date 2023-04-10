package com.flipperplz.enfusionWorkbench.psi

import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiNameIdentifierOwner

interface EnfusionNamedPsiElement : EnfusionPsiElement, PsiNameIdentifierOwner, NavigationItem