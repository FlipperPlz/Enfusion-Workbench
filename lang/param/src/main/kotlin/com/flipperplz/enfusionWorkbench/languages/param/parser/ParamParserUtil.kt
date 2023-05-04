package com.flipperplz.enfusionWorkbench.languages.param.parser

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lang.PsiBuilder
import org.intellij.grammar.parser.GeneratedParserUtilBase


object ParamParserUtil : GeneratedParserUtilBase() {
    enum class ParamStringType { DOUBLE, SINGLE, INCLUDE, UNQUOTED; }

    @JvmStatic
    fun parseMacroName(builder: PsiBuilder, level: Int): Boolean {
        if(!recursion_guard_(builder, level, "macroName")) return false
        val marker = enter_section_(builder)
        var result = consumeToken(builder, ParamTypes.IDENTIFIER)
        val name = builder.tokenText

        if(!result && name != null) result = macroExists(name)

        exit_section_(builder, marker, ParamTypes.IDENTIFIER, result)
        return result
    }

    private fun macroExists(tokenText: String): Boolean = when(tokenText) {
        "__LINE__" -> true
        "__FILE__" -> true
        "__EXEC"   -> true
        "__EVAL"   -> true
        else       -> false
    }
}

