package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.required

import com.flipperplz.enfusionWorkbench.psi.IEnfusionElementType
import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage

class ParamTokenType(debugName: String) : IEnfusionElementType(debugName, ParamLanguage) {
    override fun toString(): String = "ParamTokenType.${super.toString()}"
}
