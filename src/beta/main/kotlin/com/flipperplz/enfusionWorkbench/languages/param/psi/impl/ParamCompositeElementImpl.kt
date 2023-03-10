package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamCompositeElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

open class ParamCompositeElementImpl(
    node: ASTNode
): ASTWrapperPsiElement(node), ParamCompositeElement {
    override val tokenType: IElementType = getNode().elementType
}