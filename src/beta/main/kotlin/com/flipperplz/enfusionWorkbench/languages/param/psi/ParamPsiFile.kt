package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ParamPsiFile(
    viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, ParamLanguage) {

    override fun getFileType(): FileType = ParamFileType

    override fun toString(): String = "Param File"
}