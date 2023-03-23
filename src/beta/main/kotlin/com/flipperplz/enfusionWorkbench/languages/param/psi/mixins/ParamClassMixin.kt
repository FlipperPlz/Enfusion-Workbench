package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClassDeclaration
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNamedPsiElement

interface ParamClassMixin : ParamClassDeclaration, ParamNamedPsiElement {
    val isExternalClass: Boolean
    var classname: String?
}