package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement
import com.intellij.psi.util.parentOfType

interface ParamPsiElement : EnfusionPsiElement {
    override val binarizable: Boolean
        get() = true

//    val paramPath: String
//        get() = parentOfType<ParamPsiElement>()?.paramPath ?: "";
}