package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatementHolder
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement
import com.intellij.lang.ASTNode

open class ParamStatementHolderImpl(node: ASTNode) : ParamStatementHolder, ParamScopeComponentImpl(node) {
    override val statements: List<ParamStatement> get() = children.filterIsInstance<ParamStatement>()
    override val previousScope: ParamStatementHolder? = super.previousScope as ParamStatementHolder
    override val childScopes: List<ParamStatementHolder> = super.childScopes.map { it as ParamStatementHolder }
}