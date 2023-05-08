package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiExternalClass
import com.intellij.lang.ASTNode

open class ParamPsiExternalClassImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiExternalClass {
    override var className: String = name ?: ""
    override val isExternalParamClass: Boolean = true

    override fun toEnforce(): String = super<ParamPsiExternalClass>.toEnforce()
}