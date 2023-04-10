package com.flipperplz.enfusionWorkbench.languages.param.inspection

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamVisitor
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamStringMixin
import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.LocalQuickFixOnPsiElement
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile

class ParamAmbiguousStringInspection : LocalInspectionTool() {
    override fun getDescriptionFileName(): String = "ParamAmbiguousString.html"
    override fun getDefaultLevel(): HighlightDisplayLevel = HighlightDisplayLevel.WARNING

    override fun getGroupDisplayName(): String = "ParamAmbiguousString"
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : ParamVisitor() {
            override fun visitStringLiteral(o: ParamStringLiteral) {
                if(shouldWarn(o)) holder.registerProblem(
                    o,
                    "Ambiguous strings are considered bad-practice",
                    object : LocalQuickFixOnPsiElement(o) {
                        override fun getFamilyName(): String = "Param"
                        override fun getText(): String = "Make string double quoted."

                        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
                            startElement.replace(ParamElementFactory.createString(project, (startElement as ParamStringMixin).stringText))
                        }

                    }
                )
            }

            private fun shouldWarn(string: ParamStringLiteral) : Boolean = string.stringAmbiguousStart != null
        }
    }
}