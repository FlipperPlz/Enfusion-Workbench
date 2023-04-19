package com.flipperplz.enfusionWorkbench.psi.languages.enforce.psi.required

import com.flipperplz.enfusionWorkbench.psi.languages.enforce.EnforceLanguage
import com.flipperplz.enfusionWorkbench.psi.IEnfusionElementType

class EnforceTokenType(debugName: String) : IEnfusionElementType(debugName,
    com.flipperplz.enfusionWorkbench.psi.languages.enforce.EnforceLanguage
) {
    override fun toString(): String = "EnforceTokenType.${super.toString()}"
}
