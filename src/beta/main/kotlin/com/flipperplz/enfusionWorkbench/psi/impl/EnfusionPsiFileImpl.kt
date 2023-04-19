package com.flipperplz.enfusionWorkbench.psi.impl

import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguage
import com.flipperplz.enfusionWorkbench.psi.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

abstract class EnfusionPsiFileImpl(
    viewProvider: FileViewProvider,
    val enfusionLanguage: EnfusionLanguage
) : PsiFileBase(
    viewProvider,
    enfusionLanguage
), EnfusionPsiElement {
    abstract val enfusionFileType: EnfusionLanguageFileType

    override fun getFileType(): FileType = enfusionFileType

}