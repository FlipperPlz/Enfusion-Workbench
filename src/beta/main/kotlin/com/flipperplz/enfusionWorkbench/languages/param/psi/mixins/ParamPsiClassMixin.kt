package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.GeneratedParamRegularClassDecl
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStatementHolderImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

abstract class ParamPsiClassMixin(node: ASTNode) : ParamClass, ParamStatementHolderImpl(node) {
    override val className: ParamIdentifier = PsiTreeUtil.findChildOfType(this, ParamIdentifier::class.java) ?: throw Exception("No classname found!")
    override val isExternal: Boolean = children.firstOrNull { it is GeneratedParamRegularClassDecl } == null
    override val superClass: ParamClass?
        get() {
            val parentName = (children.firstOrNull { it is GeneratedParamRegularClassDecl} as GeneratedParamRegularClassDecl).identifier ?: return null
            return if(previousScope == null)  {
                ((containingFile as ParamPsiFile).locateClassInScope(parentName, true)) ?: throw Exception("TODO: Cant find super");
            } else previousScope!!.locateClassInScope(parentName, true) ?: throw Exception("TODO: Cant find super")
        }
    override val paramComponentName: ParamIdentifier get() = className
}