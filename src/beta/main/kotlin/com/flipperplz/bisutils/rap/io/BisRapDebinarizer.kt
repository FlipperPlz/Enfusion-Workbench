@file:Suppress("MemberVisibilityCanBePrivate")

package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.*
import com.flipperplz.bisutils.utils.getAsciiZ
import com.flipperplz.bisutils.utils.getCompactInt
import java.io.InputStream
import java.nio.ByteBuffer

object BisRapDebinarizer {
    fun debinarizeFile(inputStream: InputStream): BisRapFile? = debinarizeFile(ByteBuffer.wrap(inputStream.readAllBytes()))

    fun debinarizeFile(buffer: ByteBuffer): BisRapFile? {
        if(
           buffer.get() != 0.toByte() ||
           buffer.get() != 114.toByte() ||
           buffer.get() != 97.toByte() ||
           buffer.get() != 80.toByte() ||
           buffer.getInt() != 0 ||
           buffer.getInt() != 8
        ) return null
        return parseRap(buffer/*, RapFormatType.OP_FLASH_POINT*/)
    }

    private fun parseRap(buffer: ByteBuffer/*, format: RapFormatType*/): BisRapFile? {
        val enumOffset = buffer.getInt()
        fun loadChildClasses(child: BisRapClassStatement): Boolean {
            for(clazz in child.statements.filterIsInstance<BisRapClassStatement>()) {
                buffer.position(clazz.binaryOffset)
                clazz.superclass = buffer.getAsciiZ()

                val childCount = buffer.getCompactInt()
                val childStatements = mutableListOf<BisRapStatement>()
                for(i in 0 until childCount) childStatements.add(readStatement(buffer, clazz) ?: return false)

                clazz.statements = childStatements
                child.statements.filterIsInstance<BisRapClassStatement>().forEach {
                    if (!loadChildClasses(it)) return false
                }
            }
            return true
        }
        val statements = mutableListOf<BisRapStatement>()
        val file = BisRapFile()

        buffer.getAsciiZ()
        val entryCount = buffer.getCompactInt()
        for (i in 0 until entryCount) statements.add(readStatement(buffer, file) ?: return null)

        statements.filterIsInstance<BisRapClassStatement>().forEach {
            if (!loadChildClasses(it)) return null
        }
        //TODO: enums
        return file
    }

    private fun readStatement(buffer: ByteBuffer, elementParent: BisRapElement?): BisRapStatement? = when(buffer.get()) {
        0.toByte() -> {
            val classname = buffer.getAsciiZ()
            val offset = buffer.getInt()

            BisRapClassStatement(elementParent, offset, classname)
        }
        1.toByte() -> {
            val id = buffer.get()
            val variableName = buffer.getAsciiZ()
            val ret = BisRapParameterStatement(elementParent, variableName)
            when(id) {
                0.toByte() -> ret.tokenValue = BisRapStringLiteral(ret, buffer.getAsciiZ())
                1.toByte() -> ret.tokenValue = BisRapFloatLiteral(ret, buffer.getFloat())
                2.toByte() -> ret.tokenValue = BisRapIntegerLiteral(ret, buffer.getInt())
                else -> throw Exception()
            }
            ret
        }
        2.toByte() -> {
            val variableName = buffer.getAsciiZ()
            val ret = BisRapArrayStatement(elementParent, variableName)
            ret.tokenValue = readArray(buffer, ret)
            ret
        }
        3.toByte() -> BisRapExternalClassStatement(elementParent, buffer.getAsciiZ())
        4.toByte() -> BisRapDeleteStatement(elementParent, buffer.getAsciiZ())
        5.toByte() -> {
            if(buffer.getInt() == 1) BisRapArrayAddStatement(elementParent, buffer.getAsciiZ()).also { it.tokenValue = readArray(buffer, it) }
            else BisRapArraySubtractStatement(elementParent, buffer.getAsciiZ()).also { it.tokenValue = readArray(buffer, it) }
        }
        else -> null
    }

    private fun readArray(buffer: ByteBuffer, elementParent: BisRapElement): BisRapElement.BisRapLiteral.BisRapArray {
        val count = buffer.getCompactInt()
        val ret = BisRapArrayLiteral(elementParent)
        val entries =  mutableListOf<BisRapArrayElement>()
        for (i in 0 until count) {
            when(buffer.get()) {
                0.toByte() -> entries.add(BisRapStringLiteral(ret, buffer.getAsciiZ()))
                1.toByte() -> entries.add(BisRapFloatLiteral(ret, buffer.getFloat()))
                2.toByte() -> entries.add(BisRapIntegerLiteral(ret, buffer.getInt()))
                3.toByte() -> entries.add(readArray(buffer, ret))
                else -> throw Exception()
            }
        }

        return ret
    }

}

