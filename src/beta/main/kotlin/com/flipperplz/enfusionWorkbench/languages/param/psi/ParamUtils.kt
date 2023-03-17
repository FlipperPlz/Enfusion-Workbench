package com.flipperplz.enfusionWorkbench.languages.param.psi

fun Iterable<ParamArrayElement>.asParamArrayString(): String = "{${this.joinToString(", ") { it.asKtString()}}}"