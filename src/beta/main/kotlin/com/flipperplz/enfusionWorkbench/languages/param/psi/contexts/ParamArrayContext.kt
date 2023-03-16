package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamArrayElement

interface ParamArrayContext : ParamArrayElement, Iterable<ParamArrayElement> {
    val arrayElements: List<ParamArrayElement>
}