package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiExternalClass
import com.intellij.lang.ASTNode

open class ParamPsiExternalClassImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiExternalClass {
    override val className: String? = name
    override val isExternalParamClass: Boolean = true
}