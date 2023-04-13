package com.flipperplz.enfusionWorkbench.languages.param.psi.interfaces

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.parentOfType

interface ParamPsiClass : ParamPsiElement, PsiNameIdentifierOwner {
    val classnameIdentifier: ParamIdentifier?
    val parentClassnameIdentifier: ParamIdentifier?
    val isExternal: Boolean
    val statements: List<ParamStatement>
    val scopeCount: Int
        get() = (previousScope?.scopeCount ?: 0) + 1
    val previousScope: ParamPsiClass?
        get() = parentOfType<ParamPsiClass>()

}