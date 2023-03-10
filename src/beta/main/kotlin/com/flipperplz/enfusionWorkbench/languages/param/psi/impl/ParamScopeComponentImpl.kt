package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamScopeComponent
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

open class ParamScopeComponentImpl(node: ASTNode) : ParamNamedComponentImpl(node), ParamScopeComponent {
    override val previousScope: ParamScopeComponent? = PsiTreeUtil.findFirstParent(this) { it is ParamScopeComponent } as ParamScopeComponent
}