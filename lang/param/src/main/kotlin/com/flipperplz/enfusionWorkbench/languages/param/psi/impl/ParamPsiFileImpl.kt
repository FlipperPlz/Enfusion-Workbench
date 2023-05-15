package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.bisutils.param.node.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.childrenOfType

class ParamPsiFileImpl(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ParamLanguage), ParamPsiFile {
    override fun getFileType(): FileType = ParamFileType.instance
    override val fileName: String
        get() = containingFile.name
    override val slimCommands: List<ParamStatement>
        get() = childrenOfType<ParamGStatement>()
}
