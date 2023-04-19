package com.flipperplz.enfusionWorkbench.psi

import com.intellij.psi.tree.IElementType

abstract class EnfusionTokenType(
    debugName: String,
    val enfLanguage: EnfusionLanguage
) : IElementType(
    debugName,
    enfLanguage)

