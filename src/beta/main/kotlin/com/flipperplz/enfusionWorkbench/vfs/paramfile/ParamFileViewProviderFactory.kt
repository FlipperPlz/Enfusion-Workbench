package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamFileImpl
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.debinarizeFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.getContents
import com.intellij.lang.Language
import com.intellij.lang.LanguageParserDefinitions
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*

class ParamFileViewProviderFactory : FileViewProviderFactory {

    override fun createFileViewProvider(
        file: VirtualFile,
        language: Language?,
        manager: PsiManager,
        eventSystemEnabled: Boolean
    ): FileViewProvider {
        val project = manager.project;
        val rap = BisRapDebinarizer.debinarizeFile(file)
        val generatedFile = rap?.let {
            val contents = rap.getContents(BisRapBeautifier.NONE) ?: "//BisUtils Failed To Write BisRapFile"
            val psiFileFactory = PsiFileFactory.getInstance(project);
            psiFileFactory.createFileFromText(file.name, ParamCFileType.instance, contents)
        } ?: return ParamFileViewProvider(manager, file, eventSystemEnabled, file.fileType is ParamCFileType )


        return generatedFile.viewProvider
    }

    class ParamFileViewProvider(manager: PsiManager, file: VirtualFile, eventSystemEnabled: Boolean, val binarized: Boolean) : SingleRootFileViewProvider(manager, file, eventSystemEnabled) {
        override fun createFile(lang: Language): PsiFile = ParamFileImpl(this, binarized)

        override fun createCopy(copy: VirtualFile): SingleRootFileViewProvider {
            return ParamFileViewProvider(manager, copy, eventSystemEnabled = false, binarized = false)
        }
    }
}