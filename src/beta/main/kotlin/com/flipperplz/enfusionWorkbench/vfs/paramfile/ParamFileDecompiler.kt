package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.enfusionWorkbench.vfs.paramfile.utils.dumpFile
import com.intellij.openapi.fileTypes.BinaryFileDecompiler
import com.intellij.openapi.fileTypes.BinaryFileTypeDecompilers
import com.intellij.openapi.vfs.VirtualFile

class ParamFileDecompiler : BinaryFileDecompiler {
    override fun decompile(file: VirtualFile): CharSequence =
        BisRapDebinarizer.dumpFile(file) ?: "//Error debinarizing or printing ParamFile; Report to plugin author with the binarized version of the file."
}