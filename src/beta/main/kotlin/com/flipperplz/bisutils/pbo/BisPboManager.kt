package com.flipperplz.bisutils.pbo

import java.io.RandomAccessFile
import java.nio.channels.FileChannel

object BisPboManager {
    private val managedPbos = mutableMapOf<BisPboFile, RandomAccessFile>()

    fun managePbo(file: BisPboFile, reader: RandomAccessFile) {
        managedPbos[file] = reader
    }

    fun releasePbo(file: BisPboFile) = getPboChannel(file)?.let {
        it.lock()?.close()
        it.close()
    }

    fun getRandomAccessFile(file: BisPboFile): RandomAccessFile? {
        if(!isManaged(file)) return null
        return managedPbos[file]
    }

    fun isManaged(file: BisPboFile) = managedPbos.containsKey(file)
    fun unlockPBO(file: BisPboFile) = managedPbos[file]?.channel?.lock()?.release()

    fun lockPBO(file: BisPboFile) = managedPbos[file]?.channel?.lock()?.release()

    fun getPboChannel(file: BisPboFile): FileChannel? = managedPbos[file]?.channel


}