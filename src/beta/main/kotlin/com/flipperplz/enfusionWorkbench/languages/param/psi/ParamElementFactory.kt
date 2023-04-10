package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.tree.IElementType


object ParamElementFactory {

    fun createIdentifier(project: Project, identifierName: String): ParamIdentifier =
        (createDummyFile(project, "${identifierName}=\"dummy text\";") as ParamParameterStatement).identifier

    fun createString(project: Project, string: String, type: ParamStringType = ParamStringType.DOUBLE): ParamStringLiteral =
        (createDummyFile(project, "dummyIdentifier=${type.correctString(string)};") as ParamParameterStatement).arrayElement as ParamStringLiteral

    fun createNumber(project: Project, number: Number): ParamNumericLiteral =
        (createDummyFile(project, "dummyIdentifier=${number};") as ParamParameterStatement).arrayElement as ParamNumericLiteral

    private fun createDummyFile(project: Project, content: String): PsiFile =
        PsiFileFactory.getInstance(project).createFileFromText("__dummy-script__.cpp", ParamLanguage, content)
}