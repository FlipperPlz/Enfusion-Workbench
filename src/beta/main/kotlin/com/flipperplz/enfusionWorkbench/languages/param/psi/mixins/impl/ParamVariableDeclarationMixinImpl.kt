package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamVariableMixin
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamVariableDeclarationMixinImpl(
    node: ASTNode
) : ParamPsiElementImpl(
    node
), ParamVariableMixin {
    override val variableName: ParamIdentifier
        get() = nameIdentifier

    override val variableValue: ParamArrayElement?
        get() = arrayElement

    override fun getNameIdentifier(): ParamIdentifier = identifier

    override fun getName(): String? = nameIdentifier.name

    override fun setName(name: String): PsiElement = nameIdentifier.setName(name)
}