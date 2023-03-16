package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier

interface ParamAssignmentContext : ParamStatement {
    val assignmentName: ParamIdentifier
    val assignmentValue: ParamArrayElement

    fun isArrayAssignment(): Boolean = assignmentValue is ParamArrayContext
}