package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatementHolder

interface ParamClass : ParamStatementHolder, ParamStatement {
    val className: ParamIdentifier
    val isExternal: Boolean
    val superClass: ParamClass?
    val subclasses: List<ParamClass>
        get() = childScopes.filterIsInstance<ParamClass>()

}