package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.utils.ParamElementTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGIdentifier
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamGIdentifierMixin(node: ASTNode) : ASTWrapperPsiElement(node), ParamGIdentifier {
    override fun setName(name: String): PsiElement = TODO()
    override fun isBinarizable(): Boolean = true
    override fun toParam(): String = name ?: ""
    override fun isCurrentlyValid(): Boolean = true
    override fun getParamElementType(): ParamElementTypes = ParamElementTypes.FILE
    override fun getName(): String? = absIdentifier.text
}