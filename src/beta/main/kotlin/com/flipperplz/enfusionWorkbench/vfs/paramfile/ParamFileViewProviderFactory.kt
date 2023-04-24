package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.param.ParamFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.debinarizeFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.getContents
import com.intellij.lang.Language
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.testFramework.LightVirtualFile


class ParamFileViewProviderFactory : FileViewProviderFactory {

    override fun createFileViewProvider(
        file: VirtualFile,
        language: Language?,
        manager: PsiManager,
        eventSystemEnabled: Boolean
    ): FileViewProvider {
        val project = manager.project;
        val rap = BisRapDebinarizer.debinarizeFile(file)
        return rap?.let {
            val contents = rap.getContents(BisRapBeautifier.NONE) ?: "//BisUtils Failed To Write BisRapFile"
            val psiFileFactory = PsiFileFactory.getInstance(project);
            //instead create a virtual fake file and pass to ParamFileViewProvider
            val debinarizedFile = LightVirtualFile(file.nameWithoutExtension + ".temp", ParamLanguage, contents)
            debinarizedFile.originalFile = file
            debinarizedFile.fileType = ParamCFileType.instance

            ParamFileViewProvider(manager, debinarizedFile, eventSystemEnabled, true)
        } ?: ParamFileViewProvider(manager, file, eventSystemEnabled, false)
    }

    class ParamFileViewProvider(manager: PsiManager, file: VirtualFile, eventSystemEnabled: Boolean, val binarized: Boolean) : SingleRootFileViewProvider(manager, file, eventSystemEnabled) {
        override fun createFile(lang: Language): PsiFile = ParamFile(this, binarized)

        override fun createCopy(copy: VirtualFile): SingleRootFileViewProvider {
            return ParamFileViewProvider(manager, copy, eventSystemEnabled = false, binarized = false)
        }
    }
}