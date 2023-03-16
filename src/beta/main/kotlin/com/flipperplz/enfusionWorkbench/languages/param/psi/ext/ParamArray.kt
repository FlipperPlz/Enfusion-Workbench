package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamArrayContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

class ParamArray(node: ASTNode) : ParamCompositeElementImpl(node), ParamArrayContext {
    override val arrayElements: List<ParamArrayElement>
        get() = children.filterIsInstance<ParamArrayElement>()

    override fun iterator(): Iterator<ParamArrayElement> = arrayElements.iterator()
}