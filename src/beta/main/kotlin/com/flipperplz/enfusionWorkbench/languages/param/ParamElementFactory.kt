package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamString
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

object ParamElementFactory {

    fun createIdentifier(project: Project, identifierName: String): ParamIdentifier =
        createDummyFile(project, "${identifierName}=\"dummy text\";").firstChild as ParamIdentifier
    fun createString(project: Project, exactString: String): ParamString =
        createDummyFile(project, "dummyIdentifier=${exactString};").firstChild as ParamString


    private fun createDummyFile(project: Project, content: String): PsiFile {
        return PsiFileFactory.getInstance(project)
            .createFileFromText("__dummy-script__.c", ParamLanguage, content)
    }

}