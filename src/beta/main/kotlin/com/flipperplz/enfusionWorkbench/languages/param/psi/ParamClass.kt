package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamClass : ParamNamedStatement, ParamScope {
    val className: ParamIdentifier
    val superClass: ParamClass?
    val isExternalClass: Boolean get() = !node.textContains('{')


}