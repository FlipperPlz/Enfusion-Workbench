package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.psi.ParamPsiClassBase
import com.intellij.lang.ASTNode

abstract class ParamPsiClassBaseImpl(node: ASTNode) : ParamStatementImpl(node), ParamPsiClassBase {
    override fun getName(): String? = nameIdentifier?.name
}