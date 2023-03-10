package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamComponent

interface ParamLiteral : ParamComponent, ParamArrayElement {
    val isNumeric: Boolean
}