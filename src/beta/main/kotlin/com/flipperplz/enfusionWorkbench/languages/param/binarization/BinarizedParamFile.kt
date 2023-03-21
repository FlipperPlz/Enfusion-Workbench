package com.flipperplz.enfusionWorkbench.languages.param.binarization

import com.intellij.psi.*
import com.intellij.psi.impl.PsiFileEx
import com.intellij.psi.impl.PsiManagerImpl
import com.intellij.psi.impl.file.PsiBinaryFileImpl

class BinarizedParamFile(
    viewProvider: FileViewProvider
) : PsiBinaryFileImpl(viewProvider.manager as PsiManagerImpl, viewProvider),
    PsiCompiledFile,
    PsiFileEx,
    PsiBinaryFile {
    override fun getMirror(): PsiElement {
        TODO("Not yet implemented")
    }

    override fun getDecompiledPsiFile(): PsiFile {
        TODO("Not yet implemented")
    }


    private fun decompileToParam(): String {
        virtualFile.getUserData(PARAMC2PARAM_KEY)?.let {
            return it
        }

        TODO()
    }
}