@file:Suppress("MemberVisibilityCanBePrivate")

package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.BisRapFile
import java.io.InputStream
import java.nio.ByteBuffer

object BisRapDebinarizer {
    fun debinarizeFile(inputStream: InputStream): BisRapFile? = debinarizeFile(ByteBuffer.wrap(inputStream.readAllBytes()))

    fun debinarizeFile(buffer: ByteBuffer): BisRapFile? {

        TODO()
    }
}