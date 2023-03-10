package com.flipperplz.enfusionWorkbench.languages.param.psi.ast

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType

interface ParamCompositeElement: PsiElement {
    val tokenType: IElementType
}