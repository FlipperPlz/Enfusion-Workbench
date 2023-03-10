package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.PsiFileFactoryImpl
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.LightVirtualFile

object ParamElementGenerator {

    fun createIdentifier(project: Project, string: String): ParamIdentifier =
        PsiTreeUtil.getChildOfType(createDummyFile(project, "${string}=\"dummyIdentifier\";"), ParamIdentifier::class.java)!!

    fun createString(project: Project, string: String, inParamFormat: Boolean = false): ParamString {
        with(if(inParamFormat) string else "\"${string.replace("\"", "\"\"")}\"") {
            return PsiTreeUtil.getChildOfType(createDummyFile(project, "dummyIdentifier=${this};"), ParamString::class.java)!!
        }
    }
    fun createNumber(project: Project, string: String): ParamNumber = PsiTreeUtil.getChildOfType(
        createDummyFile(
            project,
            "dummyIdentifier=${string};"
        ),
        ParamNumber::class.java
    )!!


    fun createNumber(project: Project, float: Float): ParamNumber = PsiTreeUtil.getChildOfType(
        createDummyFile(
            project,
            "dummyIdentifier=${float};"
        ),
        ParamNumber::class.java
    )!!

    fun createNumber(project: Project, double: Double): ParamNumber = PsiTreeUtil.getChildOfType(
        createDummyFile(
            project,
            "dummyIdentifier=${double};"
        ),
        ParamNumber::class.java
    )!!

    fun createNumber(project: Project, int: Int): ParamNumber = PsiTreeUtil.getChildOfType(
        createDummyFile(
            project,
            "dummyIdentifier=${int};"
        ),
        ParamNumber::class.java
    )!!

    fun createArray(project: Project, vararg elements: ParamArrayElement): ParamArray = PsiTreeUtil.getChildOfType(
        createDummyFile(
            project,
            buildString {
                append("dummyIdentifiers[]=")
                append(ParamArray.formattedArray(elements.toList()))
                append(';')
            }
        ), ParamArray::class.java
    )!!


    private fun createDummyFile(myProject: Project, text: String): PsiFile {
        val virtualFile = LightVirtualFile("dummy." + ParamFileType.defaultExtension, ParamFileType, text)
        return (PsiFileFactory.getInstance(myProject) as PsiFileFactoryImpl).trySetupPsiForFile(virtualFile, ParamLanguage, false, true)!!
    }
}