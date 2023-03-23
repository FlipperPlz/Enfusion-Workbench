package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.intellij.lang.ASTNode

abstract class ParamNonBinaraizableMixinImpl(
    node: ASTNode
) : ParamPsiElementImpl(
    node
) {
    override val binarizable: Boolean = false
}