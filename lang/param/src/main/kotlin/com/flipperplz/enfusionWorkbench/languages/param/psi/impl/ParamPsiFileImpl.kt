package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiCommandsHolder
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ParamPsiFileImpl(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ParamLanguage), ParamPsiFile {
    override fun getFileType(): FileType = ParamFileType.instance

    override val parentCommandsHolder: ParamPsiCommandsHolder? = null

    override fun toString(): String = "Param File"

}