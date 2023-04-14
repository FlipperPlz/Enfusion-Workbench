package com.flipperplz.enfusionWorkbench.languages.param.quickfixes

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumericLiteral
import com.intellij.codeInspection.LocalQuickFixOnPsiElement
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class ReplaceIdentifierLiteralQuickFix(element: ParamIdentifier, var newValue: String) : LocalQuickFixOnPsiElement(element) {
    override fun getFamilyName(): String = "Replace identifier"
    override fun getText(): String = "Replace \"${startElement.text}\" with ${newValue}."

    override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
        val newNumericLiteral = ParamElementFactory.createIdentifier(project, newValue).node

        startElement.node.replaceChild(startElement.node.firstChildNode, newNumericLiteral.firstChildNode)
    }

}