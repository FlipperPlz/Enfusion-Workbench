package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamNamedComponentImpl
import com.intellij.lang.ASTNode

abstract class ParamPsiIdentifierMixin(node: ASTNode) : ParamNamedComponentImpl(node), ParamIdentifier {
    override val identifierName: String = text.lowercase()
}