package com.flipperplz.enfusionWorkbench.languages.param.utils

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.psi.tree.IElementType

enum class ParamStringType(
    private val id: Int,
    val startToken: IElementType? = ParamTypes.STRING_START,
    val endToken: IElementType? = ParamTypes.STRING_END
) {
    DOUBLE(0),
    SINGLE(1),
    INCLUDE(2, ParamTypes.INCLUDE_START, ParamTypes.INCLUDE_END),
    NONE(3),

    NOT_STRING(-1, null, null)
}