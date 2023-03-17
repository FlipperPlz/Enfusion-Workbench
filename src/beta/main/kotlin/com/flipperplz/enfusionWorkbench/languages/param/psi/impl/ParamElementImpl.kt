package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

open class ParamElementImpl(
    node: ASTNode
) : ASTWrapperPsiElement(
    node
), ParamElement