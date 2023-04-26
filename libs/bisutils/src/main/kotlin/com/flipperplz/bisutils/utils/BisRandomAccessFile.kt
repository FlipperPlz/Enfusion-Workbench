package com.flipperplz.bisutils.utils

import java.io.File
import java.io.RandomAccessFile

class BisRandomAccessFile(val file: File, mode: String) : RandomAccessFile(file, mode) {

}