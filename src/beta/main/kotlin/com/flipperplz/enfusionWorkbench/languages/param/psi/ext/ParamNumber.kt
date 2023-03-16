package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamNumericLiteralContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

open class ParamNumber(node: ASTNode) : ParamCompositeElementImpl(node), ParamNumericLiteralContext {
    override fun asKotlinNumber(): Number {
        return text.toIntOrNull() ?: text.toFloatOrNull() ?: text.toDoubleOrNull() ?: throw Exception("Failed to parse ParamNumber.")
    }

    override fun asKotlinString(): String = text
}