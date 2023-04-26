@file:Suppress("MemberVisibilityCanBePrivate")

package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.*
import com.flipperplz.bisutils.utils.getAsciiZ
import com.flipperplz.bisutils.utils.getCompactInt
import com.flipperplz.bisutils.utils.getFloat
import com.flipperplz.bisutils.utils.getInt
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object BisRapDebinarizer {
    fun debinarizeFile(inputStream: InputStream): BisRapFile? = debinarizeFile(ByteBuffer.wrap(inputStream.readAllBytes()))

    fun debinarizeFile(file: File) : BisRapFile? = with(FileInputStream(file)) { val rap = debinarizeFile(this); close(); rap  }

    fun debinarizeFile(buffer: ByteBuffer): BisRapFile? {
        if(buffer.get() != 0.toByte()) return null
        if(buffer.get() != 114.toByte()) return null
        if(buffer.get() != 97.toByte()) return null
        if(buffer.get() != 80.toByte()) return null
        if(buffer.getInt(ByteOrder.LITTLE_ENDIAN) != 0) return null
        if(buffer.getInt(ByteOrder.LITTLE_ENDIAN) != 8) return null


        return parseRap(buffer/*, RapFormatType.OP_FLASH_POINT*/)
    }

    private fun parseRap(buffer: ByteBuffer/*, format: RapFormatType*/): BisRapFile? {
        val enumOffset = buffer.getInt(ByteOrder.LITTLE_ENDIAN)
        val file = BisRapFile()

        buffer.getAsciiZ()
        val entryCount = buffer.getCompactInt()
        for (i in 0 until entryCount) {
            file.statements.add(readStatement(buffer, file) ?: return null)
        }
        println("kk")
        file.statements.filterIsInstance<BisRapClassStatement>().forEach {
            if (!loadChildClasses(it, buffer)) return null
        }

        buffer.position(enumOffset) //TODO: ENUMS
        return file
    }

    private fun loadChildClasses(child: BisRapClassStatement, buffer: ByteBuffer): Boolean {
        buffer.position(child.binaryOffset)
        child.superclass = buffer.getAsciiZ()

        val childCount = buffer.getCompactInt()
        for(i in 0 until childCount) child.statements.add(readStatement(buffer, child) ?: return false)

        child.statements.filterIsInstance<BisRapClassStatement>().forEach {
            if (!loadChildClasses(it, buffer)) return false
        }
        return true
    }

    private fun readStatement(buffer: ByteBuffer, elementParent: BisRapElement?): BisRapStatement? {
        val v = buffer.get()
        return when(v) {
            0.toByte() -> readClassStatement(elementParent, buffer)
            1.toByte() -> readParameterStatement(elementParent, buffer)
            2.toByte() -> readArrayStatement(elementParent, buffer)
            3.toByte() -> readExternalClass(elementParent, buffer.getAsciiZ())
            4.toByte() -> readDelete(elementParent, buffer.getAsciiZ())
            5.toByte() -> readArrayFlaggedStatement(elementParent, buffer)
            else -> null
        }
    }

    private fun readClassStatement(elementParent: BisRapElement?, buffer: ByteBuffer): BisRapClassStatement {
        val classname = buffer.getAsciiZ()
        val offset = buffer.getInt(ByteOrder.LITTLE_ENDIAN)

        return BisRapClassStatement(elementParent, offset, classname)
    }


    private fun readParameterStatement(elementParent: BisRapElement?, buffer: ByteBuffer): BisRapParameterStatement {
        val id = buffer.get()
        val variableName = buffer.getAsciiZ()
        val param = BisRapParameterStatement(elementParent, variableName)
        when(id) {
            0.toByte() -> param.tokenValue = BisRapStringLiteral(param, buffer.getAsciiZ())
            1.toByte() -> param.tokenValue = BisRapFloatLiteral(param, buffer.getFloat(ByteOrder.LITTLE_ENDIAN))
            2.toByte() -> param.tokenValue = BisRapIntegerLiteral(param, buffer.getInt(ByteOrder.LITTLE_ENDIAN))
            else -> throw Exception()
        }
        return param
    }


    private fun readArrayStatement(elementParent: BisRapElement?, buffer: ByteBuffer): BisRapBaseArrayStatement {
        val variableName = buffer.getAsciiZ()
        val ret = BisRapArrayStatement(elementParent, variableName)
        ret.tokenValue = parseArray(buffer, ret)
        return ret
    }

    private fun readArrayFlaggedStatement(elementParent: BisRapElement?, buffer: ByteBuffer): BisRapBaseArrayStatement = if(buffer.getInt() == 1)
        BisRapArrayAddStatement(elementParent, buffer.getAsciiZ()).also { it.tokenValue = parseArray(buffer, it) }
    else BisRapArraySubtractStatement(elementParent, buffer.getAsciiZ()).also { it.tokenValue = parseArray(buffer, it) }
    private fun readDelete(elementParent: BisRapElement?, asciiZ: String): BisRapDeleteStatement = BisRapDeleteStatement(elementParent, asciiZ)

    private fun readExternalClass(elementParent: BisRapElement?, asciiZ: String): BisRapExternalClassStatement = BisRapExternalClassStatement(elementParent, asciiZ)

    private fun parseArray(buffer: ByteBuffer, elementParent: BisRapElement): BisRapElement.BisRapLiteral.BisRapArray {
        val count = buffer.getCompactInt()
        val arrayEntry = BisRapArrayLiteral(elementParent)
        for (i in 0 until count) {
            when(buffer.get()) {
                0.toByte() -> arrayEntry.value.add(BisRapStringLiteral(arrayEntry, buffer.getAsciiZ()))
                1.toByte() -> arrayEntry.value.add(BisRapFloatLiteral(arrayEntry, buffer.getFloat(ByteOrder.LITTLE_ENDIAN)))
                2.toByte() -> arrayEntry.value.add(BisRapIntegerLiteral(arrayEntry, buffer.getInt(ByteOrder.LITTLE_ENDIAN)))
                3.toByte() -> arrayEntry.value.add(parseArray(buffer, arrayEntry))
                else -> throw Exception()
            }
        }

        return arrayEntry
    }


}

