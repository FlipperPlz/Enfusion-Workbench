package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamPsiElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement

abstract class ParamIdentifierMixin(
    node: ASTNode
) : ParamIdentifier, PsiNameIdentifierOwner, ParamPsiElementImpl(
    node
) {
    override fun getName(): String? = text
    override fun setName(name: String): PsiElement {
        val newIdentifier = ParamElementFactory.createIdentifier(project, name).node

        node.replaceChild(node.firstChildNode, newIdentifier.firstChildNode)

        return this
    }

    override fun getNameIdentifier(): PsiElement = this

}