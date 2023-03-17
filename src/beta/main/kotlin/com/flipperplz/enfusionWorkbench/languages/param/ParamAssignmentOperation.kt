package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lang.ASTFactory
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

enum class ParamAssignmentOperation(
    private val operator: IElementType?,
    private val operatorText: String
) {
    ASSIGN(null, "="),
    SUB_ASSIGN(ParamTypes.OP_SUB_ASSIGN, "-="),
    ADD_ASSIGN(ParamTypes.OP_ADD_ASSIGN, "+=");


    fun createOperator(): ASTNode =
        ASTFactory.leaf(operator ?: ParamTypes.OP_ASSIGN, operatorText)

    companion object {
        fun getAssignmentType(node: ASTNode): ParamAssignmentOperation = when {
            node.findChildByType(ParamTypes.OP_SUB_ASSIGN) != null -> SUB_ASSIGN
            node.findChildByType(ParamTypes.OP_ADD_ASSIGN) != null -> ADD_ASSIGN
            else -> ASSIGN
        }
    }
}