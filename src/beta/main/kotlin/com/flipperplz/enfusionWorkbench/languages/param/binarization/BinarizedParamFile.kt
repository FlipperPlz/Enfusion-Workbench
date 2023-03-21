package com.flipperplz.enfusionWorkbench.languages.param.binarization

import com.flipperplz.enfusionWorkbench.languages.param.binarization.io.ParamCReader
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamFile
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.*
import com.intellij.psi.impl.PsiFileEx
import com.intellij.psi.impl.PsiManagerImpl
import com.intellij.psi.impl.file.PsiBinaryFileImpl
import com.intellij.util.PathUtil

class BinarizedParamFile(
    viewProvider: FileViewProvider
) : PsiBinaryFileImpl(viewProvider.manager as PsiManagerImpl, viewProvider),
    PsiCompiledFile,
    PsiFileEx,
    PsiBinaryFile,
    ParamFile{

    private val decompiledStatements: List<ParamStatement> by lazy {
        ParamCReader.readFile(project, virtualFile).statements
    }

    override val statements: List<ParamStatement>
        get() = children.filterIsInstance<ParamStatement>()

    override fun getChildren(): Array<PsiElement> = decompiledStatements.toTypedArray()

    override fun getMirror(): PsiElement = this

    override fun getDecompiledPsiFile(): PsiFile = this

    override val paramName: String
        get() = name.substring(name.length - (PathUtil.getFileExtension(name)?.length ?: 0))

    override fun isPBOConfig(): Boolean = name == "config.cpp" || name == "config.bin"

    override fun isWorkshopConfig(): Boolean = name == "mod.cpp" || name == "mod.bin"

    override fun isRVMAT(): Boolean = name.endsWith(".rvmat")

    override fun hasBinExtension(): Boolean = name.endsWith(".bin")

    override fun getFileType(): FileType = ParamBinFileType
}