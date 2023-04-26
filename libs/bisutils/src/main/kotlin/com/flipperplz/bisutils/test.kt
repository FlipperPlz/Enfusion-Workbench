package com.flipperplz.bisutils

import com.flipperplz.bisutils.rap.io.BisRapDebinarizer
import com.flipperplz.bisutils.rap.io.BisRapWriter
import java.io.File

fun main() {
    val file = BisRapDebinarizer.debinarizeFile(File("E:\\dayz\\example.bin")) ?: return
    BisRapWriter.writeTo(file, File("C:\\Users\\developer\\Desktop\\ai.pbo"))
}