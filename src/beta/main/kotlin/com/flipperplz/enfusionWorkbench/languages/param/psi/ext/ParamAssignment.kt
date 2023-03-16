package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamAssignmentContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

open class ParamAssignment(node: ASTNode) : ParamCompositeElementImpl(node), ParamAssignmentContext {
    override val assignmentName: ParamIdentifier
        get() = children.firstOrNull { it is ParamIdentifier } as ParamIdentifier
    override val assignmentValue: ParamArrayElement
        get() = children.filterIsInstance<ParamArrayElement>().first()
}