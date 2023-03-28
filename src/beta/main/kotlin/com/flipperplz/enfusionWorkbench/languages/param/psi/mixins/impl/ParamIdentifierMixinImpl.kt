package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamConcatenateDirective
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamIdentifierImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamIdentifierMixin
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamIdentifierMixinImpl(
    node: ASTNode
) : ParamPsiElementImpl(
    node
), ParamIdentifierMixin {

    override fun getName(): String? = identifierName

    override fun setName(name: String): PsiElement = replace(ParamElementFactory.createIdentifier(name))

    override fun getNameIdentifier(): PsiElement? = this
};