package com.flipperplz.bisutils.languages.param.psi.enumerations

import com.flipperplz.bisutils.languages.param.psi.ParamTypes
import com.intellij.psi.tree.IElementType

enum class ParamStringType(
    private val id: Int,
    val startElement: IElementType?,
    val endElement: IElementType?,
    val badChars: String? = null
) {
    DOUBLE(0, ParamTypes.STRING_DOUBLE_START, ParamTypes.STRING_DOUBLE_END, "\n\r"),
    SINGLE(1, ParamTypes.STRING_SINGLE_START, ParamTypes.STRING_SINGLE_END, "\n\r"),
    INCLUDE(2, ParamTypes.STRING_INCLUDE_START, ParamTypes.STRING_INCLUDE_END, "\n\r>"),
    UNQUOTED(3, ParamTypes.STRING_AMBIGUOUS_START, ParamTypes.STRING_AMBIGUOUS_END, "\n\r;},");

}