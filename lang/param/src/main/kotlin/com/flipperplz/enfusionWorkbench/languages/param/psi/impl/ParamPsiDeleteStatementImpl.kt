package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.bisutils.param.slim.ParamSlimDeleteStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiNamedElement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

abstract class ParamPsiDeleteStatementImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiNamedElement, ParamSlimDeleteStatement {

    override fun getNameIdentifier(): ParamIdentifier? = childrenOfType<ParamIdentifier>().firstOrNull()

    override var target: String
        get() = name ?: ""
        set(value) { setName(name ?: "unknown") }

    override fun toEnforce(): String = super<ParamSlimDeleteStatement>.toEnforce()

}