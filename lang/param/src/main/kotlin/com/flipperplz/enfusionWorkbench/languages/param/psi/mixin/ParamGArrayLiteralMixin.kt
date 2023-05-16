package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.ParamLiteralBase
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGArrayLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGArrayElementImpl
import com.intellij.lang.ASTNode

abstract class ParamGArrayLiteralMixin(node: ASTNode) : ParamGArrayElementImpl(node), ParamGArrayLiteral {
    override val slimValue: List<ParamLiteralBase>
        get() = literalList
}