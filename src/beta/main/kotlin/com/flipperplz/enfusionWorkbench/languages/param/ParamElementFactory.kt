package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.childrenOfType

object ParamElementFactory {

    fun createIdentifier(project: Project, identifierName: String): ParamIdentifier =
        createDummyFile(project, "${identifierName}=\"dummy text\";").firstChild as ParamIdentifier

    fun createString(project: Project, exactString: String): ParamString =
        createDummyFile(project, "dummyIdentifier=${exactString};").childrenOfType<ParamString>().first()

    fun createNumber(project: Project, number: Number): ParamNumeric =
        createDummyFile(project, "dummyIdentifier=${number};").childrenOfType<ParamNumeric>().first()

    fun createArray(project: Project, vararg element: ParamArrayElement): ParamArray =
        createDummyFile(project, "dummyIdentifier=${element.asIterable().asParamArrayString()};").childrenOfType<ParamArray>().first()

    fun createArray(project: Project, elements: List<ParamArrayElement>): ParamArray =
        createDummyFile(project, "dummyIdentifier=${elements.asParamArrayString()};").childrenOfType<ParamArray>().first()

    private fun createDummyFile(project: Project, content: String): PsiFile =
        PsiFileFactory.getInstance(project).createFileFromText("__dummy-script__.c", ParamLanguage, content)

}