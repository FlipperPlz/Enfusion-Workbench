package com.flipperplz.enfusionWorkbench.psi.languages.param.inspections

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.*
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.util.Condition
import com.intellij.openapi.util.Conditions.*
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil


abstract class AbstractBaseParamLocalInspectionTool : LocalInspectionTool() {
    companion object {
        private val PROBLEM_ELEMENT_CONDITION: Condition<PsiElement> = instanceOf(
            ParamFile::class.java,
            ParamClassStatement::class.java,
            ParamParameterStatement::class.java
        )
    }

    /**
     * Override this to report problems at class level.
     *
     * @param paramFile  to check.
     * @param manager    InspectionManager to ask for ProblemDescriptors from.
     * @param isOnTheFly true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     * @return `null` if no problems found or not applicable at file level.
     */
    open fun checkFile(
        paramFile: ParamFile,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        return null
    }

    /**
     * Override this to report problems at class level.
     *
     * @param aClass     to check.
     * @param manager    InspectionManager to ask for ProblemDescriptors from.
     * @param isOnTheFly true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     * @return `null` if no problems found or not applicable at class level.
     */
    open fun checkClass(
        aClass: ParamClassStatement,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        return null
    }

    /**
     * Override this to report problems at field level.
     *
     * @param literal    to check.
     * @param manager    InspectionManager to ask for ProblemDescriptors from.
     * @param isOnTheFly true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     * @return `null` if no problems found or not applicable at literal level.
     */
    open fun checkLiteral(
        literal: ParamLiteral,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        return null
    }

    /**
     * Override this to report problems at field level.
     *
     * @param parameter  to check.
     * @param manager    InspectionManager to ask for ProblemDescriptors from.
     * @param isOnTheFly true if called during on the fly editor highlighting. Called from Inspect Code action otherwise.
     * @return `null` if no problems found or not applicable at parameter level.
     */
    open fun checkParameter(
        parameter: ParamParameterStatement,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        return null
    }
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : ParamVisitor() {
            override fun visitParameterStatement(parameter: ParamParameterStatement) { addDescriptors(holder, checkParameter(parameter, holder.manager, isOnTheFly)) }

            override fun visitClassStatement(clazz: ParamClassStatement) { addDescriptors(holder, checkClass(clazz, holder.manager, isOnTheFly)) }

            override fun visitFile(file: PsiFile) { addDescriptors(holder, checkFile(file, holder.manager, isOnTheFly)) }

            override fun visitLiteral(literal: ParamLiteral) { addDescriptors(holder, checkLiteral(literal, holder.manager, isOnTheFly)) }
        }
    }

    protected fun addDescriptors(holder: ProblemsHolder, descriptors: Array<ProblemDescriptor>?) = descriptors?.let { problems ->
        problems.forEach { problem ->
            holder.registerProblem(problem)
        }
    }

    final override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        if(file !is ParamFile) return super.checkFile(file, manager, isOnTheFly)
        return this.checkFile(file, manager, isOnTheFly)
    }

    final override fun getProblemElement(psiElement: PsiElement): PsiNamedElement? =
        PsiTreeUtil.findFirstParent(psiElement, PROBLEM_ELEMENT_CONDITION) as? PsiNamedElement
}