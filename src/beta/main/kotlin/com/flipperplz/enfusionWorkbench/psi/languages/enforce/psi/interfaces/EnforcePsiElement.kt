package com.flipperplz.enfusionWorkbench.psi.languages.enforce.psi.interfaces

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement

interface EnforcePsiElement : EnfusionPsiElement {
    override val binarizable: Boolean
        get() = false
}