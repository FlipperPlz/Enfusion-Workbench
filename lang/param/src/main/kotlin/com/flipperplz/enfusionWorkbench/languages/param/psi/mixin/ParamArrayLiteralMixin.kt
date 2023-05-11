package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.bisutils.param.node.RapLiteralBase
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamArrayLiteralMixin(node: ASTNode) : ParamLiteralImpl(node), ParamArrayLiteral {
    override val slimValue: List<RapLiteralBase>
        get() = arrayElementList
    override val slimParent: RapElement?
        get() = parent as? RapElement
}