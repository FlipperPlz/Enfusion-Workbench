package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.utils.ParamElementTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGEnumValue
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ParamGEnumValueMixin(node: ASTNode) : ASTWrapperPsiElement(node), ParamGEnumValue {
    override fun isBinarizable(): Boolean = true

    override fun toParam(): String = ""

    override fun isCurrentlyValid(): Boolean = true

    override fun getParamElementType(): ParamElementTypes = ParamElementTypes.C_ENUM
}