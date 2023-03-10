package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamComponent

interface ParamIdentifier : ParamComponent {
    val identifierName: String
}