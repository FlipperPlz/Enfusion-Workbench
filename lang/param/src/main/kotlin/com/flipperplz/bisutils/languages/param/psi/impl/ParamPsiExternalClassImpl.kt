package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.psi.ParamPsiExternalClass
import com.intellij.lang.ASTNode

open class ParamPsiExternalClassImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiExternalClass {
    override val className: String? = name
    override val isExternalParamClass: Boolean = true
}