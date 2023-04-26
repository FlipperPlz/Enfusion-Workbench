package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.psi.ParamPsiStatementsHolder
import com.flipperplz.bisutils.languages.param.psi.ParamStatement
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

class ParamPsiClassImpl(node: ASTNode) : ParamPsiClassBaseImpl(node), ParamPsiStatementsHolder {
    override val statements: List<ParamStatement>
        get() = childrenOfType()
}