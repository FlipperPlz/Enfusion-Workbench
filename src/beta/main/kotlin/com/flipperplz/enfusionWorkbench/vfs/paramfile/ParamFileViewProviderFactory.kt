package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamFileImpl
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.debinarizeFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.getContents
import com.intellij.lang.Language
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
        rap?.let {
            //return PsiFileFactory.getInstance(project).createFileFromText(file.name + ".debin", ParamCFileType.instance, rap.getContents(BisRapBeautifier.NONE) ?: "//error").viewProvider
        }
        return object : SingleRootFileViewProvider(manager, file, eventSystemEnabled, language ?: ParamLanguage) {
            override fun createFile(project: Project, file: VirtualFile, fileType: FileType): PsiFile =
                ParamFileImpl(viewProvider = this, isExternal = true, isBinarizedFile = false)
        }
    }

}