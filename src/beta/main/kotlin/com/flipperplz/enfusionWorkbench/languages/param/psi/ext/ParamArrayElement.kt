package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamComponent

interface ParamArrayElement : ParamComponent {
    fun writeAsParam(): String;
}