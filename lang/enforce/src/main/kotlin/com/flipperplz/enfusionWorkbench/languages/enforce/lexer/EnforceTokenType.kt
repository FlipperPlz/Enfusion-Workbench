package com.flipperplz.enfusionWorkbench.languages.enforce.lexer

import com.flipperplz.enfusionWorkbench.languages.enforce.EnforceLanguage
import com.intellij.psi.tree.IElementType

class EnforceTokenType(debugName: String) : IElementType(debugName, EnforceLanguage) {
    override fun toString(): String = "EnforceTokenType.${super.toString()}"
}