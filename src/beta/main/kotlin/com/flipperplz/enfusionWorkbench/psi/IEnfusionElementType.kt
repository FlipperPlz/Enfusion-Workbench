package com.flipperplz.enfusionWorkbench.psi

import com.intellij.psi.tree.IElementType

abstract class IEnfusionElementType(
    debugName: String,
    val enfLanguage: EnfusionLanguage
) : IElementType(debugName, enfLanguage)