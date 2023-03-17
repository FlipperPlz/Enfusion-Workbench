package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamFile
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.childrenOfType
import com.intellij.util.PathUtil

open class ParamFileImpl(
    viewProvider: FileViewProvider
) : PsiFileBase(
    viewProvider,
    ParamLanguage
), ParamFile {

    override val statements: List<ParamStatement>
        get() = childrenOfType()

    override val paramName: String
        get() = name.substring(name.length - (PathUtil.getFileExtension(name)?.length ?: 0))

    override fun getFileType(): FileType = ParamFileType

    override fun toString(): String = "Param File"

    override fun isPBOConfig(): Boolean = name == "config.cpp" || name == "config.bin"

    override fun isWorkshopConfig(): Boolean = name == "mod.cpp" || name == "mod.bin"

    override fun isRVMAT(): Boolean = name.endsWith(".rvmat")

    override fun hasBinExtension(): Boolean = name.endsWith(".bin")

}