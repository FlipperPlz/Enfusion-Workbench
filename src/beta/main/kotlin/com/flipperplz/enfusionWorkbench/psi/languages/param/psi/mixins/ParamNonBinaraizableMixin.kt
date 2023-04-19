package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamPsiElementImpl
import com.intellij.lang.ASTNode

abstract class ParamNonBinaraizableMixin(
    node: ASTNode
) : ParamPsiElementImpl(
    node
) {
    override val binarizable: Boolean = false
}