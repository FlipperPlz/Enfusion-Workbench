package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.interfaces.ParamPsiElement
import com.flipperplz.enfusionWorkbench.psi.impl.EnfusionPsiElementImpl
import com.intellij.lang.ASTNode

abstract class ParamPsiElementImpl(
    node: ASTNode
) : EnfusionPsiElementImpl(
    node
), ParamPsiElement