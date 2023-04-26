package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.psi.ParamPsiClassBase
import com.intellij.lang.ASTNode

open class ParamPsiExternalClassImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiClassBase {
    override val className: String? = name
    override val isExternalParamClass: Boolean = true
}