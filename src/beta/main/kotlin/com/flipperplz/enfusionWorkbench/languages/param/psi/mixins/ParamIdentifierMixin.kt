package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNamedPsiElement

interface ParamIdentifierMixin : ParamNamedPsiElement, ParamIdentifier {
    val identifierName: String get() = text

}
