package com.flipperplz.enfusionWorkbench.languages.param.lexer

import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.intellij.psi.tree.IElementType

class ParamTokenType(debugName: String) : IElementType(debugName, ParamLanguage) {
    override fun toString(): String = "ParamTokenType.${super.toString()}"
}