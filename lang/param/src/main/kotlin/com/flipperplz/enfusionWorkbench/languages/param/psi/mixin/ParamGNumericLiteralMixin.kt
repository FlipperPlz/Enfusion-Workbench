package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.utils.ParamElementTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGNumericLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGLiteralImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamGNumericLiteralMixin(node: ASTNode) : ParamGLiteralImpl(node), ParamGNumericLiteral {
    override fun isBinarizable(): Boolean = true
    override fun getParamElementType(): ParamElementTypes =
            if(slimValue is Float) ParamElementTypes.L_FLOAT else ParamElementTypes.L_INT
    override val slimValue: Number?
        get() = absNumeric.text.toIntOrNull() ?: absNumeric.text.toFloatOrNull()
}