package com.flipperplz.enfusionWorkbench.languages.param.psi.required

import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.IEnfusionElementType

class ParamTokenType(debugName: String) : IEnfusionElementType(debugName, ParamLanguage) {
    override fun toString(): String = "ParamTokenType.${super.toString()}"
}
