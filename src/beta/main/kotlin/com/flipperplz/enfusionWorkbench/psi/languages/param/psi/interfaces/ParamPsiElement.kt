package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.parentOfType

interface ParamPsiElement : EnfusionPsiElement {
    override val binarizable: Boolean
        get() = true
    val paramFile: ParamPsiStatementScope?
        get() = containingFile as? ParamPsiStatementScope

    val paramElementName: String
        get() = if(this is PsiNamedElement) this.name ?: "" else childrenOfType<PsiNameIdentifierOwner>().first().name ?: ""

    val paramPath: String
        get() = "${parentOfType<ParamPsiElement>()?.paramPath}.$paramElementName";

}