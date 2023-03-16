package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamLiteral

interface ParamStringLiteralContext : ParamLiteral {
    fun asKotlinString(returnQuoted: Boolean = true): String
}