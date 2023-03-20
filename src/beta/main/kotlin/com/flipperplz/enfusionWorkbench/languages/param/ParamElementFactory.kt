package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamAssignmentOperation
import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

object ParamElementFactory {

    fun createIdentifier(project: Project, identifierName: String): ParamIdentifier =
        (createDummyFile(project, "${identifierName}=\"dummy text\";") as ParamAssignment).paramAssignee

    fun createString(project: Project, quotedString: String): ParamString =
        (createDummyFile(project, "dummyIdentifier=${quotedString};") as ParamAssignment).paramValue as ParamString

    fun createNumber(project: Project, number: Number): ParamNumeric =
        (createDummyFile(project, "dummyIdentifier=${number};") as ParamAssignment).paramValue as ParamNumeric

    fun createArray(project: Project, vararg element: ParamArrayElement): ParamArray =
        (createDummyFile(project, "dummyIdentifier=${element.asIterable().asParamArrayString()};") as ParamAssignment).paramValue as ParamArray

    fun createArray(project: Project, elements: List<ParamArrayElement>): ParamArray =
        (createDummyFile(project, "dummyIdentifier=${elements.asParamArrayString()};") as ParamAssignment).paramValue as ParamArray

    fun createAssignment(project: Project, tokenName: String, value: ParamArrayElement, operation: ParamAssignmentOperation = ParamAssignmentOperation.ASSIGN): ParamAssignment =
        (createDummyFile(project, "${if(value is ParamArray && !tokenName.contains("\\[\\s*]\\s*")) "${tokenName}[]" else tokenName}${operation.operatorText}${value.asKtString()}") as ParamAssignment)

    fun createDelete(project: Project, deleting: String): ParamDelete =
        (createDummyFile(project, "delete ${deleting};") as ParamDelete)

    private fun createDummyFile(project: Project, content: String): PsiFile =
        PsiFileFactory.getInstance(project).createFileFromText("__dummy-script__.c", ParamLanguage, content)

}