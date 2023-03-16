package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamIdentifierContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

open class ParamIdentifier(node: ASTNode) : ParamCompositeElementImpl(node), ParamIdentifierContext {
    override val identifierName: String = text.lowercase()
}