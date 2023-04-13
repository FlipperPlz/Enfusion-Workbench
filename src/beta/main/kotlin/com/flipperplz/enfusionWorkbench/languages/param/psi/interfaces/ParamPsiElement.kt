package com.flipperplz.enfusionWorkbench.languages.param.psi.interfaces

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.parentOfType

interface ParamPsiElement : EnfusionPsiElement {
    override val binarizable: Boolean
        get() = true

    val paramElementName: String
        get() = childrenOfType<PsiNameIdentifierOwner>().first().name ?: ""

    val paramPath: String
        get() = "${parentOfType<ParamPsiElement>()?.paramPath}.$paramElementName";

}