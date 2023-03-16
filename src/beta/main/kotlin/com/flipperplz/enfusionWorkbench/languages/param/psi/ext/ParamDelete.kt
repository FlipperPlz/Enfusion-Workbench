package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamClassContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamDeleteContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

class ParamDelete(node: ASTNode) : ParamCompositeElementImpl(node), ParamDeleteContext {
    override val deleting: ParamIdentifier = children.filterIsInstance<ParamIdentifier>().first()
}