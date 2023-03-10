package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamNumber
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

abstract class ParamPsiNumericMixin(node: ASTNode) : ParamNumber, ParamCompositeElementImpl(node) {
    override fun writeAsParam(): String = value.toString()
}