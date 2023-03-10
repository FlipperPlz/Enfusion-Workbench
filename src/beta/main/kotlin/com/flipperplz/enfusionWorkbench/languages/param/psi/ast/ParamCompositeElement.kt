package com.flipperplz.enfusionWorkbench.languages.param.psi.ast

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.tree.IElementType

interface ParamCompositeElement: NavigatablePsiElement {
    val tokenType: IElementType
}