package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.GeneratedParamRegularClassDecl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamScopeComponent
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamScopeComponentImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

abstract class ParamPsiClassMixin(node: ASTNode) : ParamClass, ParamScopeComponentImpl(node) {
    override val className: ParamIdentifier = PsiTreeUtil.findChildOfType(this, ParamIdentifier::class.java) ?: throw Exception("No classname found!")
    override val isExternal: Boolean = children.firstOrNull { it is GeneratedParamRegularClassDecl } == null
    override val superClass: ParamClass? = null
    override val previousScope: ParamScopeComponent? = PsiTreeUtil.findFirstParent(this) { it is ParamScopeComponent } as ParamScopeComponent
    override val paramComponentName: ParamIdentifier get() = className
    override val statements: List<ParamStatement> get() = children.filterIsInstance<ParamStatement>()
}