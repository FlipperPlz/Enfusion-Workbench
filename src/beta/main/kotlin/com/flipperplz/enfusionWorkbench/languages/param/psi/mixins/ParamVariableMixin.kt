package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNamedPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamVariableDeclaration

interface ParamVariableMixin : ParamVariableDeclaration, ParamNamedPsiElement {
    val variableName: ParamIdentifier
    val variableValue: ParamArrayElement?

    val isArrayVariable: Boolean get() = this.symLsquare != null && this.symRsquare != null
}