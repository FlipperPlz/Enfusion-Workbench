package com.flipperplz.enfusionWorkbench.languages.param.psi.ast

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement

interface ParamNamedComponent: ParamComponent, PsiNameIdentifierOwner, PsiNamedElement {
    val paramComponentName: ParamIdentifier
}