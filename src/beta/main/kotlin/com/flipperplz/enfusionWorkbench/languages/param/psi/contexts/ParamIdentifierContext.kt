package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamComponent

interface ParamIdentifierContext : ParamComponent {
    val identifierName: String
}