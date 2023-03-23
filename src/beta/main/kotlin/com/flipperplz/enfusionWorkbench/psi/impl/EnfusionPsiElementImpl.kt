package com.flipperplz.enfusionWorkbench.psi.impl

import com.flipperplz.enfusionWorkbench.psi.EnfusionPsiElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class EnfusionPsiElementImpl(
    node: ASTNode
) : ASTWrapperPsiElement(
    node
), EnfusionPsiElement