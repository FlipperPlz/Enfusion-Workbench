package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamAssignment: ParamNamedStatement {
    fun getAssignee(): ParamIdentifier
    fun getValue(): GeneratedParamArrayElement

    fun isArrayAssignment(): Boolean = textContains('[') && getValue() is GeneratedParamLiteralArray
    fun isArrayAppend(): Boolean = node.findChildByType(ParamTypes.OP_ADD_ASSIGN) != null && isArrayAssignment()
    fun isArrayRemove(): Boolean = node.findChildByType(ParamTypes.OP_SUB_ASSIGN) != null && isArrayAssignment()


    override fun setName(name: String): PsiElement = getAssignee().setName(name)
}
