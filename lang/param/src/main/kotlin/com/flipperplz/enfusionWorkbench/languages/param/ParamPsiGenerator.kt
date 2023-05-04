package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamCommand
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamExternalClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumericLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamParameterStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.util.applyIf
import com.intellij.webSymbols.utils.applyIfNotNull

fun createIdentifier(identifierName: String, project: Project): ParamIdentifier =
    createStatement(identifierName, null, project).identifier

fun createExternal(className: String, project: Project): ParamExternalClassStatement =
    createDummyFile(project, "class ${className};").firstChild as ParamExternalClassStatement

fun createClass(className: String, superClass: String?, commands: List<ParamCommand>, project: Project): ParamClassStatement =
    (createDummyFile(project, "class ${className}${if(superClass != null) ": $superClass" else ""} {\n};") as ParamClassStatement).applyIf(commands.isNotEmpty()) {
    val iterator = commands.listIterator()

    var anchor = this.symLcurly!!
    var current: PsiElement
    do {
        current = iterator.next()
        anchor = this.addAfter(anchor, current)
    } while (iterator.hasNext())

    this
}

fun createNumber(int: Int, project: Project): ParamNumericLiteral = (createDummyFile(project, "test=${int.toString()};").firstChild as ParamParameterStatement).literal as ParamNumericLiteral

fun createStatement(parameterName: String, parameterValue: ParamLiteral?, project: Project): ParamParameterStatement =
    (createDummyFile(project, "${parameterName}=\"\";").firstChild as ParamParameterStatement).applyIfNotNull(parameterName) {
        this.literal!!.replace(parameterValue!!)
        this
    }

fun createStatement(parameterName: String, parameterValue: ParamArrayLiteral, project: Project): ParamParameterStatement =
    (createDummyFile(project, "${parameterName}[]=\"\";").firstChild as ParamParameterStatement).apply {
        this.literal!!.replace(parameterValue)
    }

private fun createDummyFile(project: Project, content: String): ParamPsiFile = PsiFileFactory.getInstance(project)
    .createFileFromText("__dummy-script__.cpp", ParamFileType.instance, content) as ParamPsiFile
