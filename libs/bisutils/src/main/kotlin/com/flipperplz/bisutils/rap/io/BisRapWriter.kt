package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.*
import com.flipperplz.bisutils.rap.io.formatting.BisRapBeautifier
import java.io.File
import java.io.FileWriter
import java.nio.file.Path


object BisRapWriter {

    fun toString(file: BisRapFile, beautifier: BisRapBeautifier = BisRapBeautifier.NONE): String = writeElement(file, beautifier).toString()

    fun writeTo(rapFile: BisRapFile, file: File, beautifier: BisRapBeautifier = BisRapBeautifier.NONE) {
        val fileWriter = FileWriter(file)
        fileWriter.flush()
        fileWriter.write(toString(rapFile, beautifier))
        fileWriter.close()
    }

    fun writeTo(rapFile: BisRapFile, path: Path, beautifier: BisRapBeautifier = BisRapBeautifier.NONE) = BisRapWriter.writeTo(rapFile, path.toFile(), beautifier)

    private fun writeElement(element: BisRapElement, beautifier: BisRapBeautifier = BisRapBeautifier.NONE, previousBuilder: StringBuilder? = null): StringBuilder {
        with(previousBuilder ?: StringBuilder()) {
            return when(element) {
                is BisRapElement.BisRapFile -> this.also { for (statement in element.statements) it.append(writeElement(statement, beautifier)) }
                is BisRapArrayLiteral ->  this.also {
                    it.append("{")
                    beautifier.arrayStart2ArrayElement(it)
                    for ((index, item) in element.value.withIndex()) {
                        if (index > 0) {
                            beautifier.arrayElement2Comma(it)
                            it.append(",")
                            beautifier.comma2ArrayElement(it)
                            writeElement(item, beautifier, it)
                        } else {
                            writeElement(item, beautifier, it)
                            beautifier.arrayElement2ArrayEnd(it)
                        }
                    }
                    it.append("}")
                }
                is BisRapNumericLiteral -> this.append(element.value.toString())
                is BisRapStringLiteral -> this.append("\"${element.value}\"")
                is BisRapClassStatement -> this.also {
                    beautifier.beforeClassKw(it)
                    it.append("class")
                    beautifier.classKw2Classname(it)
                    it.append(element.classname)
                    if(element.superclass.isNotBlank()) {
                        beautifier.classname2Colon(it)
                        it.append(':')
                        beautifier.colon2SuperName(it)
                        it.append(element.superclass)
                        beautifier.superName2LCurly(it)
                    } else {
                        beautifier.className2LCurly(it)
                    }
                    it.append('{')
                    beautifier.lCurly2ClassBody(it)
                    for (statement in element.statements) it.append(writeElement(statement, beautifier))
                    beautifier.classBody2RCurly(it)
                    it.append('}')
                    beautifier.rCurly2Semi(it)
                    it.append(';')
                    beautifier.afterClassStatement(it)
                }
                is BisRapExternalClassStatement -> this.also {
                    beautifier.beforeClassKw(it)
                    it.append("class")
                    beautifier.classKw2Classname(it)
                    it.append(element.classname)
                    beautifier.classname2Semi(it)
                    it.append(";")
                    beautifier.afterExternalClassStatement(it)
                }
                is BisRapDeleteStatement -> this.also {
                    beautifier.beforeDeleteKw(it)
                    it.append("delete")
                    beautifier.deleteKw2DeleteTarget(it)
                    it.append(element.target)
                    beautifier.deleteTarget2Semi(it)
                    it.append(';')
                    beautifier.afterDeleteStatement(it)
                }
                is BisRapBaseParameterStatement -> this.also {
                    beautifier.beforeVariableName(it)
                    it.append(element.tokenName)
                    val isArray = element is BisRapBaseArrayStatement
                    if(isArray) {
                        beautifier.arrayName2LSquare(it)
                        it.append('[')
                        beautifier.lSquare2RSquare(it)
                        it.append(']')
                        beautifier.rSquare2Operator(it)
                    } else beautifier.varName2Operator(it)
                    when (element) {
                        is BisRapArrayAddStatement -> it.append("+=")
                        is BisRapArraySubtractStatement -> it.append("-=")
                        else -> it.append('=')
                    }
                    if(isArray) beautifier.operator2ArrayStart(it)
                    else beautifier.operator2VariableValue(it)
                    writeElement(element.tokenValue, beautifier, it)
                    if(isArray) {
                        beautifier.arrayEnd2Semi(it);
                        it.append(';')
                        beautifier.afterArrayStatement(it)
                    } else {
                        beautifier.variableValue2Semi(it)
                        it.append(';')
                        beautifier.afterVariableStatement(it)
                    }
                }
            }
        }
    }
}