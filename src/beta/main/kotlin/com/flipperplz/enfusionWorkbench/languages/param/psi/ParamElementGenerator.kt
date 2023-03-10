package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.PsiFileFactoryImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.LightVirtualFile

object ParamElementGenerator {

    fun createIdentifier(project: Project, string: String): ParamIdentifier =
        PsiTreeUtil.getChildOfType(createDummyFile(project, "${string}=\"dummyIdentifier\";"), ParamIdentifier::class.java)!!


    private fun createDummyFile(myProject: Project, text: String): PsiFile {
        val virtualFile = LightVirtualFile("dummy." + ParamFileType.defaultExtension, ParamFileType, text)
        return (PsiFileFactory.getInstance(myProject) as PsiFileFactoryImpl).trySetupPsiForFile(virtualFile, ParamLanguage, false, true)!!
    }
}