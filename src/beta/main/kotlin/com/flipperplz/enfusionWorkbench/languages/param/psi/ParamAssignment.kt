package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamAssignmentOperation
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

interface ParamAssignment: ParamNamedStatement {
    val paramAssignee: ParamIdentifier
    val paramValue: ParamArrayElement
    val currentOperator: ASTNode
        get() = ParamAssignmentOperation.getCurrentOperator(this)

    var operationType: ParamAssignmentOperation
        get() = ParamAssignmentOperation.getAssignmentType(node)
        set(value) {
            node.replaceChild(currentOperator, value.createOperator())
        }

    fun isArrayAssignment(): Boolean = textContains('[') && paramValue is ParamArray


    override fun setName(name: String): PsiElement = paramAssignee.setName(name)

    override fun asParsableText(): String {
        TODO("Not yet implemented")
    }
}
