package com.flipperplz.enfusionWorkbench.vfs.paramfile

import com.flipperplz.enfusionWorkbench.vfs.paramfile.paramC.ParamCFileType
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.util.io.ByteSequence
import com.intellij.openapi.vfs.VirtualFile

class ParamFileTypeDetector : FileTypeRegistry.FileTypeDetector {
    /**
     * Detects file type by its (possibly binary) content on disk. Your detector must be as light as possible.
     * In particular, it must not perform any heavy processing, e.g. PSI access, indices, documents, etc.
     * The detector must refrain from throwing exceptions (including pervasive [ProcessCanceledException])
     *
     * @param file             to analyze
     * @param firstBytes       of the file for identifying its file type
     * @param firstCharsIfText - characters converted from `firstBytes` parameter if the file content was determined to be text, `null` otherwise
     * @return detected file type, or null if was unable to detect
     */
    override fun detect(file: VirtualFile, firstBytes: ByteSequence, firstCharsIfText: CharSequence?): FileType? {

        println()
        
        if (firstBytes.byteAt(0).toInt() == 0 && firstBytes.byteAt(1).toInt() == 114 && firstBytes.byteAt(2)
                .toInt() == 97 && firstBytes.byteAt(2).toInt() == 80
        ) return ParamCFileType.instance

        return null
    }
}
