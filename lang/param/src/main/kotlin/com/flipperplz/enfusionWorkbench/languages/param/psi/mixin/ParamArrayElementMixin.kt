package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.utils.ParamLiteralTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ParamArrayElementMixin(node: ASTNode) : ASTWrapperPsiElement(node), ParamArrayElement {
    override val slimLiteralType: ParamLiteralTypes
        get() = literal.slimLiteralType
    override val slimValue: Any?
        get() = literal.slimValue
    override val literalId: Byte?
        get() = literal.literalId
    override val slimParent: RapElement?
        get() = literal.slimParent

    override fun toParam(): String = literal.toParam()

}