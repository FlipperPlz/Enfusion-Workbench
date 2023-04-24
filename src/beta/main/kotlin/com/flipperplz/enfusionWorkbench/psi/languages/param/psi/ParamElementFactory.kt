package com.flipperplz.enfusionWorkbench.psi.languages.param.psi

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.utils.ParamStringType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.param.ParamFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory


object ParamElementFactory {

    fun createIdentifier(project: Project, identifierName: String): ParamIdentifier =
        createDummyFile(project, "${identifierName}=\"dummy text\";").firstStatementOfType<ParamParameterStatement>()!!.identifier

    fun createString(project: Project, string: String, type: ParamStringType = ParamStringType.DOUBLE): ParamStringLiteral =
        createDummyFile(project, "dummyIdentifier=${type.correctString(string)};").firstStatementOfType<ParamParameterStatement>()!!.arrayElement as ParamStringLiteral

    fun createNumber(project: Project, number: Number): ParamNumericLiteral =
        createDummyFile(project, "dummyIdentifier=${number};").firstStatementOfType<ParamParameterStatement>()!!.arrayElement as ParamNumericLiteral

    private fun createDummyFile(project: Project, content: String): ParamFile = PsiFileFactory.getInstance(project)
        .createFileFromText("__dummy-script__.cpp", ParamFileType.instance, content) as ParamFile
}