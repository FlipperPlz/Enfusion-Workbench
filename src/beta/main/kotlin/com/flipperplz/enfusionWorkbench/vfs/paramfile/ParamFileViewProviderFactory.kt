package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamFileImpl
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.debinarizeFile
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.getContents
import com.intellij.lang.Language
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
        if(file.length >= 4) {
            val rap = BisRapDebinarizer.debinarizeFile(file)
            rap?.let {
                return object : SingleRootFileViewProvider(manager, file, eventSystemEnabled, language ?: ParamLanguage) {
                    val contents = it.getContents(BisRapBeautifier.NONE)

                    override fun getContents(): CharSequence = contents!!

                    override fun createFile(project: Project, file: VirtualFile, fileType: FileType): PsiFile =
                        ParamFileImpl(viewProvider = this, isExternal = true, isBinarizedFile = true)
                }
            }

        }
        return object : SingleRootFileViewProvider(manager, file, eventSystemEnabled, language ?: ParamLanguage) {
            override fun createFile(project: Project, file: VirtualFile, fileType: FileType): PsiFile =
                ParamFileImpl(viewProvider = this, isExternal = true, isBinarizedFile = false)
        }
    }

}