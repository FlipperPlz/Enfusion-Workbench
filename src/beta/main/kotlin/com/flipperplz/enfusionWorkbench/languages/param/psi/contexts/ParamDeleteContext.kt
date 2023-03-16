package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatement

interface ParamDeleteContext : ParamStatement {
    val deleting: ParamIdentifierContext
}