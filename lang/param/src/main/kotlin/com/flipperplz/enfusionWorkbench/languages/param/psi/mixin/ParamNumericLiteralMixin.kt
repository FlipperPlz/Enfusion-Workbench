package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.utils.ParamLiteralTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumericLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamNumericLiteralMixin(node: ASTNode) : ParamLiteralImpl(node), ParamNumericLiteral {
    override val slimLiteralType: ParamLiteralTypes
        get() = if (slimValue is Float) ParamLiteralTypes.FLOAT else ParamLiteralTypes.INTEGER
    override val slimValue: Number?
        get() = text.toIntOrNull() ?: text.toFloatOrNull()
    override val literalId: Byte?
        get() = if (slimValue is Float) 1 else 2
    override val slimParent: RapElement?
        get() = parent as? RapElement

}