package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.ParamFileType
import com.flipperplz.bisutils.languages.param.ParamLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ParamTextFileImpl(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ParamLanguage)  {
    override fun getFileType(): FileType = ParamFileType.instance

    override fun toString(): String = "Param File"
}