package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ParamPsiFileImpl(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ParamLanguage) {
    override fun getFileType(): FileType = ParamFileType.instance
}
