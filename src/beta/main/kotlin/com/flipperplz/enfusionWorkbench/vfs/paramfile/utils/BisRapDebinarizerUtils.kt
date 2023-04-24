package com.flipperplz.enfusionWorkbench.vfs.paramfile.utils

import com.flipperplz.bisutils.rap.BisRapFile
import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.BisRapWriter
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import com.intellij.openapi.vfs.VirtualFile

fun BisRapDebinarizer.debinarizeFile(file: VirtualFile): BisRapFile? {
    val stream = file.inputStream

    if(stream.available() < 4) {
        stream.close()
        return null
    }
    val rap = debinarizeFile(stream);

    stream.close()
    return rap
}

fun BisRapDebinarizer.dumpFile(file: VirtualFile): String? = debinarizeFile(file)?.getContents(BisRapBeautifier.NONE)

fun BisRapFile?.getContents(beautifier: BisRapBeautifier): String? = if(this == null) null else BisRapWriter.toString(this, beautifier)