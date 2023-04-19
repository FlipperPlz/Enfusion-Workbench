package com.flipperplz.enfusionWorkbench.psi.languages.param.quickfixes

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamNumericLiteral
import com.intellij.codeInspection.LocalQuickFixOnPsiElement
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class ReplaceNumericLiteralQuickFix(element: ParamNumericLiteral, var newValue: Number) : LocalQuickFixOnPsiElement(element) {
    override fun getFamilyName(): String = "Replace number"
    override fun getText(): String = "Replace \"${startElement.text}\" with ${newValue}."

    override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
        val newNumericLiteral = ParamElementFactory.createNumber(project, newValue).node

        startElement.node.replaceChild(startElement.node.firstChildNode, newNumericLiteral.firstChildNode)
    }

}