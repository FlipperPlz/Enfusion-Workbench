package com.flipperplz.enfusionWorkbench.languages.param.psi


interface ParamArray : ParamArrayElement, Iterable<ParamArrayElement> {
    val arrayElements: List<ParamArrayElement>
}