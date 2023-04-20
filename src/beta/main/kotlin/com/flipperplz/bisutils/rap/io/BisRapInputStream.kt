package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.BisRapFile
import java.io.InputStream
import java.nio.ByteBuffer

class BisRapInputStream(buffer: ByteBuffer) {
    constructor(inputStream: InputStream) : this(ByteBuffer.wrap(inputStream.readAllBytes()))

    fun createFile(): BisRapFile? {

        TODO()
    }
}