package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArray
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

abstract class ParamArrayMixin(node: ASTNode) : ParamElementImpl(node), ParamArray {
    override val arrayElements: List<ParamArrayElement>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, ParamArrayElement::class.java)

    override fun iterator(): Iterator<ParamArrayElement> = arrayElements.iterator()

}