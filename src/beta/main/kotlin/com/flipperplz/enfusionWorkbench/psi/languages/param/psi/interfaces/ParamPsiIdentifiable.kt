package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamIdentifier
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.childrenOfType

interface ParamPsiIdentifiable : ParamPsiElement, PsiNameIdentifierOwner {

    override fun getNameIdentifier(): ParamIdentifier? = childrenOfType<ParamIdentifier>().first()
    override fun getName(): String? = nameIdentifier?.name
    override fun setName(name: String): PsiElement? = nameIdentifier?.setName(name)
}