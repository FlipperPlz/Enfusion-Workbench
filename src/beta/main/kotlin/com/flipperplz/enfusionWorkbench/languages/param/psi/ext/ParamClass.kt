package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamScopeComponent

interface ParamClass : ParamScopeComponent, ParamStatement {
    val className: ParamIdentifier
    val isExternal: Boolean
    val superClass: ParamClass?
    val statements: List<ParamStatement>
}