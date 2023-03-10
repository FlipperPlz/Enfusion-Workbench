package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElementGenerator
import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamNamedComponent
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.NavigationItem
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

open class ParamNamedComponentImpl(node: ASTNode) : ParamCompositeElementImpl(node), ParamNamedComponent {
    override val paramComponentName: ParamIdentifier = PsiTreeUtil.getChildOfType(this, ParamIdentifier::class.java) ?: throw Exception("BisPSI (param): Named Element With No Identifier")

    override fun setName(name: String): PsiElement {
        node.replaceChild(paramComponentName.node, ParamElementGenerator.createIdentifier(project, name).node)

        return this
    }

    override fun getName(): String = paramComponentName.identifierName

    override fun getNameIdentifier(): PsiElement = this

    override fun getPresentation(): ItemPresentation? = if (parent is NavigationItem) {
        (parent as NavigationItem).presentation
    } else null

}