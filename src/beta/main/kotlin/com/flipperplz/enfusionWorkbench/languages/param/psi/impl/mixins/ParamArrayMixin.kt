package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArray
import com.flipperplz.enfusionWorkbench.languages.param.psi.asParamArrayString
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

abstract class ParamArrayMixin(node: ASTNode) : ParamElementImpl(node), ParamArray {
    override val arrayElements: List<ParamArrayElement>
        get() = childrenOfType()

    override fun iterator(): Iterator<ParamArrayElement> = arrayElements.iterator()

    override fun asKtString(): String = arrayElements.asParamArrayString()
}