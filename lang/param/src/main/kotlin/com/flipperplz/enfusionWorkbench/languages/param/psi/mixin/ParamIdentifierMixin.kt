package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamIdentifierMixin(node: ASTNode) : ASTWrapperPsiElement(node), ParamIdentifier {
    override fun setName(name: String): PsiElement = TODO("Not yet implemented")
    override fun getName(): String? = text
}