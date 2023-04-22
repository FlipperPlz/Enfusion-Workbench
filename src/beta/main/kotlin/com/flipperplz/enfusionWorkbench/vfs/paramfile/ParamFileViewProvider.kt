package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.BisRapWriter
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamFileImpl
import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.psi.SingleRootFileViewProvider
import java.io.InputStream

class ParamFileViewProvider(
    manager: PsiManager,
    val file: VirtualFile,
    eventSystemEnabled: Boolean,
    language: Language
) : SingleRootFileViewProvider(manager, file, eventSystemEnabled, language) {

    override fun createFile(lang: Language): PsiFile? {
        val stream = file.inputStream
        if(stream.available() >= 4) {
            val b = ByteArray(4)
            stream.read(b, 0, 4)
            if(b[0] == 0.toByte() && b[1] == 'r'.toByte() && b[2] == 'a'.toByte()&& b[3] == 'P'.toByte()) {
                return PsiFileFactory.getInstance(manager.project).createFileFromText(
                    "binarizedCfg",
                    ParamLanguage,
                    createStreamFromBinarizedParamFile(stream)
                )
            }
        }

        return super.createFile(lang)
    }

    private fun createStreamFromBinarizedParamFile(binarizedStream: InputStream): CharSequence =
        BisRapWriter.toString(BisRapDebinarizer.debinarizeFile(binarizedStream)!!)
}