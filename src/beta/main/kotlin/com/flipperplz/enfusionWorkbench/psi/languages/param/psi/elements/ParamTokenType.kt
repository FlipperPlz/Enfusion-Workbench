package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.elements

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.intellij.psi.tree.IElementType

class ParamTokenType(debugName: String) : IElementType(debugName, ParamLanguage) {
    override fun toString(): String = "ParamTokenType.${super.toString()}"
}
