package com.flipperplz.enfusionWorkbench.languages.param.utils

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamStringMixin
import com.intellij.psi.tree.IElementType

enum class ParamStringType(
    private val id: Int,
    val startToken: IElementType?,
    val endToken: IElementType?
) {
    DOUBLE(0, ParamTypes.STRING_DOUBLE_START, ParamTypes.STRING_DOUBLE_END),
    SINGLE(1, ParamTypes.STRING_SINGLE_START, ParamTypes.STRING_SINGLE_END),
    INCLUDE(2, ParamTypes.STRING_INCLUDE_START, ParamTypes.STRING_INCLUDE_END),
    AMBIGUOUS(3, ParamTypes.STRING_AMBIGUOUS_START, ParamTypes.STRING_AMBIGUOUS_END),

    NOT_STRING(-1, null, null);

    fun escapedString(literal: ParamStringLiteral): String = when(this) {
        AMBIGUOUS -> literal.text
        SINGLE -> literal.text.trimStart('\'').trimEnd('\'').replace("\'\'", "\'");
        DOUBLE -> literal.text.trimStart('\"').trimEnd('\"').replace("\"\"", "\"");
        INCLUDE -> literal.text.trimStart('<').trimEnd('>')
        else -> ""
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