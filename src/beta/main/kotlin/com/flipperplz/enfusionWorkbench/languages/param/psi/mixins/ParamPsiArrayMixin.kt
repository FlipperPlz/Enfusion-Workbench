package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamArray
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamArrayElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamPsiArrayMixin(node: ASTNode) : ParamCompositeElementImpl(node), ParamArray, List<ParamArrayElement> by emptyList() {

    private val arrayChildren:  List<ParamArrayElement> = children.filterIsInstance<ParamArrayElement>()

    override fun iterator(): Iterator<ParamArrayElement> = arrayChildren.iterator()

    override fun contains(element: ParamArrayElement): Boolean = children.contains(element as PsiElement)

    override fun isEmpty(): Boolean = arrayChildren.isEmpty()

    override fun containsAll(elements: Collection<ParamArrayElement>): Boolean {
        elements.forEach { if(!contains(it)) return false }
        return true
    }

    override val size: Int = Short.MAX_VALUE.toInt()

    override fun indexOf(element: ParamArrayElement): Int = arrayChildren.indexOf(element)

    override fun lastIndexOf(element: ParamArrayElement): Int = arrayChildren.lastIndexOf(element)

    override fun get(index: Int): ParamArrayElement = arrayChildren[(index)]

    override fun listIterator(index: Int): ListIterator<ParamArrayElement> = arrayChildren.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): List<ParamArrayElement> = arrayChildren.subList(fromIndex, toIndex)

    override fun listIterator(): ListIterator<ParamArrayElement> = arrayChildren.listIterator()
}