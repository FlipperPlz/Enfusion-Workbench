package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.debinarizeFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.getContents
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*

class ParamFileViewProviderFactory : FileViewProviderFactory {
    override fun createFileViewProvider(
        file: VirtualFile,
        language: Language?,
        manager: PsiManager,
        eventSystemEnabled: Boolean
    ): FileViewProvider = ParamFileViewProvider(manager, file, eventSystemEnabled, file.fileType is ParamCFileType)
    class ParamFileViewProvider(manager: PsiManager, file: VirtualFile, eventSystemEnabled: Boolean, val binarized: Boolean) : SingleRootFileViewProvider(manager, file, eventSystemEnabled) {
        override fun createFile(lang: Language): PsiFile = ParamFile(this, binarized)

        override fun createCopy(copy: VirtualFile): SingleRootFileViewProvider =
            ParamFileViewProvider(manager, copy, eventSystemEnabled = false, binarized = false)
    }
}