package com.flipperplz.bisutils

import com.flipperplz.bisutils.pbo.BisPboFile
import com.flipperplz.bisutils.utils.BisRandomAccessFile
import java.nio.channels.FileChannel

object BisPboManager {
    private val managedPbos = mutableMapOf<BisPboFile, BisRandomAccessFile>()

    fun managePbo(file: BisPboFile, reader: BisRandomAccessFile) {
        managedPbos[file] = reader
    }

    fun releasePbo(file: BisPboFile) = getPboChannel(file)?.let {
        it.lock()?.close()
        it.close()
    }

    fun getRandomAccessFile(file: BisPboFile): BisRandomAccessFile? {
        if(!isManaged(file)) return null
        return managedPbos[file]
    }

    fun isManaged(file: BisPboFile) = managedPbos.containsKey(file)
    fun unlockPBO(file: BisPboFile) = managedPbos[file]?.channel?.lock()?.release()

    fun lockPBO(file: BisPboFile) = managedPbos[file]?.channel?.lock()?.release()

    fun getPboChannel(file: BisPboFile): FileChannel? = managedPbos[file]?.channel


}