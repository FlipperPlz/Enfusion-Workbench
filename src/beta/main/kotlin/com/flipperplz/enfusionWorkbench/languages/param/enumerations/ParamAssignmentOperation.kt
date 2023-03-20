package com.flipperplz.enfusionWorkbench.languages.param.enumerations

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamAssignment
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lang.ASTFactory
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

enum class ParamAssignmentOperation(
    private val operator: IElementType?,
    val operatorText: String
) {
    ASSIGN(null, "="),
    SUB_ASSIGN(ParamTypes.OP_SUBASSIGN, "-="),
    ADD_ASSIGN(ParamTypes.OP_ADDASSIGN, "+=");


    fun createOperator(): ASTNode =
        ASTFactory.leaf(operator ?: ParamTypes.OP_ASSIGN, operatorText)

    companion object {
        fun getAssignmentType(node: ASTNode): ParamAssignmentOperation = when {
            node.findChildByType(ParamTypes.OP_SUBASSIGN) != null -> SUB_ASSIGN
            node.findChildByType(ParamTypes.OP_ADDASSIGN) != null -> ADD_ASSIGN
            else -> ASSIGN
        }

        fun getCurrentOperator(assignment: ParamAssignment): ASTNode = with(assignment.node) {
            this.findChildByType(ParamTypes.OP_ASSIGN) ?:
            this.findChildByType(ParamTypes.OP_ADDASSIGN) ?:
            this.findChildByType(ParamTypes.OP_SUBASSIGN)!!
        }
    }
}