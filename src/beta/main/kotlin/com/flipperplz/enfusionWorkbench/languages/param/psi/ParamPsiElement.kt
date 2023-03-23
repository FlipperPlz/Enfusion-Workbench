package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement

interface ParamPsiElement : EnfusionPsiElement {
    override val binarizable: Boolean
        get() = true
}