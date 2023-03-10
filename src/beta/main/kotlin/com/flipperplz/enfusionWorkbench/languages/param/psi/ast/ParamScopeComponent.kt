package com.flipperplz.enfusionWorkbench.languages.param.psi.ast

import com.intellij.psi.util.PsiTreeUtil

interface ParamScopeComponent : ParamNamedComponent {
    val previousScope: ParamScopeComponent?
    val childScopes: List<ParamScopeComponent>
        get() = PsiTreeUtil.getChildrenOfType(this, ParamScopeComponent::class.java)?.toList() ?: emptyList()
}