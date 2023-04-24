package com.flipperplz.enfusionWorkbench.psi.languages.param.psi

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces.ParamPsiStatementScope
import com.flipperplz.enfusionWorkbench.vfs.paramfile.param.ParamFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType

sealed interface ParamFile : PsiFile, ParamPsiStatementScope {
    val inBinaryContext: Boolean

    companion object {
        operator fun invoke(viewProvider: FileViewProvider): ParamFile = ParamTextFile(viewProvider)
    }

    override val isExternal: Boolean
        get() = false

    override val binarizable: Boolean
        get() = inBinaryContext || super.binarizable

    override fun getNameIdentifier(): PsiElement? = null

    override val statements: List<ParamStatement>
        get() = childrenOfType()

    class ParamTextFile(
        viewProvider: FileViewProvider
    ) : PsiFileBase(viewProvider, ParamLanguage), ParamFile {
        override fun getFileType(): FileType = if(inBinaryContext) ParamFileType.instance else ParamCFileType.instance
        override val inBinaryContext: Boolean = false
    }
}
