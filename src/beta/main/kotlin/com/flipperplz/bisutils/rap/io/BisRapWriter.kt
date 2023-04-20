package com.flipperplz.bisutils.rap.io

import com.flipperplz.bisutils.rap.*


object BisRapWriter {

    fun writeElement(element: BisRapElement, beautifier: BisRapBeautifier = BisRapBeautifier.OFF): String {
        return when(element) {
            is BisRapElement.BisRapFile -> {
                element.statements.joinToString("\n") {
                    writeElement(it, beautifier)
                } + element.enums.entries.joinToString(",\n", prefix = "\nenum {\n", postfix = "\n};") {
                    "\t" + it.key + " = " + it.value
                }
            }
            is BisRapArrayLiteral -> {
                element.value.joinToString(", ", prefix = "{ ", postfix = " }") { writeElement(it, beautifier) }
            }
            is BisRapNumericLiteral -> element.value.toString()
            is BisRapStringLiteral -> "\"${element.value.toString()}\""
            is BisRapClassStatement -> {
                var string = "${beautifier.indent}class ${element.classname} "
                if (element.superclass.isNotBlank()) { string += ": ${element.superclass} " }
                beautifier.pushIndentation()
                return "{\n${element.statements.joinToString("\n") { writeElement(it, beautifier) }}\n${beautifier.popIndent}};"
            }
            is BisRapExternalClassStatement -> "${beautifier.indent}class ${element.classname};"
            is BisRapDeleteStatement -> "${beautifier.indent}delete ${element.target};"
            is BisRapBaseParameterStatement -> element.tokenName + when(element) {
                is BisRapArrayStatement -> " = "
                is BisRapParameterStatement -> " = "
                is BisRapArrayAddStatement -> " + "
                is BisRapArraySubtractStatement -> " -= "
            } + writeElement(element.tokenValue, beautifier) + ";"
        }
    }

}