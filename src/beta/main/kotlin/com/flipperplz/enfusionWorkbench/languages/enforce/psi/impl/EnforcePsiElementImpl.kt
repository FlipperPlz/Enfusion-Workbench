package com.flipperplz.enfusionWorkbench.languages.enforce.psi.impl

import com.flipperplz.enfusionWorkbench.languages.enforce.psi.interfaces.EnforcePsiElement
import com.flipperplz.enfusionWorkbench.psi.impl.EnfusionPsiElementImpl
import com.intellij.lang.ASTNode

abstract class EnforcePsiElementImpl(node: ASTNode) : EnfusionPsiElementImpl(node), EnforcePsiElement {
}