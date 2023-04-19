package com.flipperplz.enfusionWorkbench.psi.languages.param.utils

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamStringContent
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamTypes
import com.intellij.psi.tree.IElementType

enum class ParamStringType(
    private val id: Int,
    val startToken: IElementType?,
    val endToken: IElementType?,
    val badChars: String? = null
) {
    DOUBLE(0, ParamTypes.STRING_DOUBLE_START, ParamTypes.STRING_DOUBLE_END, "\n\r"),
    SINGLE(1, ParamTypes.STRING_SINGLE_START, ParamTypes.STRING_SINGLE_END, "\n\r"),
    INCLUDE(2, ParamTypes.STRING_INCLUDE_START, ParamTypes.STRING_INCLUDE_END, "\n\r>"),
    AMBIGUOUS(3, ParamTypes.STRING_AMBIGUOUS_START, ParamTypes.STRING_AMBIGUOUS_END, "\n\r;},"),

    NOT_STRING(-1, null, null);

    fun prune(literal: ParamStringLiteral?): String = if(literal == null) "" else when(this) {
        AMBIGUOUS -> literal.text
        SINGLE -> literal.text.trimStart('\'').trimEnd('\'').replace("\'\'", "\'");
        DOUBLE -> literal.text.trimStart('\"').trimEnd('\"').replace("\"\"", "\"");
        INCLUDE -> literal.text.trimStart('<').trimEnd('>')
        else -> ""
    }

    fun prune(content: ParamStringContent?): String = if(content == null) "" else when(this) {
        AMBIGUOUS -> content.text
        SINGLE -> content.text.replace("\'\'", "\'");
        DOUBLE -> content.text.replace("\"\"", "\"");
        INCLUDE -> content.text
        else -> ""
    }

    fun usingCorrectDelimiters(literal: ParamStringLiteral) = detectStringType(literal) == this
    fun replaceStartToken(literal: ParamStringLiteral) {
        if(usingCorrectDelimiters(literal)) return
        if(this == NOT_STRING) throw Exception("what in the fuck")
//
//        when(this) {
//            DOUBLE -> {
//                if(literal.stringDoubleStart != null)  literal.stringDoubleStart!!.replace()
//            }
//        }
//        if(literalstringDoubleStart != null)  stringDoubleStart!!.replace(value.startToken)
    }

    fun correctString(string: String): String {
        var out: String = string
        when(this) {
            AMBIGUOUS -> if(badChars != null && out.contains(Regex.fromLiteral(badChars))) out = "BadString"
            SINGLE -> {
                out = out.trimStart('\'').trimEnd('\'');
                if(badChars != null && string.contains(Regex.fromLiteral(badChars))) out = "BadString"
                out = "'${out}'"
            }
            DOUBLE -> {
                out = out.trimStart('\"').trimEnd('\"');
                if(badChars != null && string.contains(Regex.fromLiteral(badChars))) out = "BadString"
                out = "\"${out}\""
            }
            INCLUDE -> {
                out = out.trimStart('<').trimEnd('>');
                if(badChars != null && string.contains(Regex.fromLiteral(badChars))) out = "BadString"
                out = "<${out}>"
            }
            else -> out = "BadString"
        }
        return out;
    }

    companion object {
        public fun detectStringType(stringLiteral: ParamStringLiteral): ParamStringType = when {
            stringLiteral.stringDoubleStart != null && stringLiteral.stringDoubleEnd != null ->
                DOUBLE
            stringLiteral.stringSingleStart != null && stringLiteral.stringSingleEnd != null ->
                SINGLE
            stringLiteral.stringAmbiguousStart != null && stringLiteral.stringAmbiguousEnd != null ->
                DOUBLE
            else -> NOT_STRING
        }
    }
}