package com.flipperplz.enfusionWorkbench.languages.enforce.psi.required

import com.flipperplz.enfusionWorkbench.languages.enforce.EnforceLanguage
import com.flipperplz.enfusionWorkbench.psi.IEnfusionElementType

class EnforceTokenType(debugName: String) : IEnfusionElementType(debugName, EnforceLanguage) {
    override fun toString(): String = "EnforceTokenType.${super.toString()}"
}
