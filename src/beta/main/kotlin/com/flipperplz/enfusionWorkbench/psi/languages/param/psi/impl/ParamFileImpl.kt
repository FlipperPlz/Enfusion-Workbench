package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces.ParamFile
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces.ParamPsiElement
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class ParamFileImpl(
    viewProvider: FileViewProvider,
    override val isBinarizedFile: Boolean = true
) : PsiFileBase(viewProvider, ParamLanguage), ParamFile {





}