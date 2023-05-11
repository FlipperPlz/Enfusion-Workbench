package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamParameterReference
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamParameterReferenceMixin(node: ASTNode) : ParamLiteralImpl(node), ParamParameterReference {
    override val slimValue: String?
        get() = stringLiteral?.slimValue
    override val slimParent: RapElement?
        get() = parent as? RapElement
}