package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamIdentifierMixin(
    node: ASTNode
): ParamElementImpl(node), ParamIdentifier {
    override fun setName(name: String): PsiElement = replace(ParamElementFactory.createIdentifier(project, name))
    override fun getName(): String? = text
}