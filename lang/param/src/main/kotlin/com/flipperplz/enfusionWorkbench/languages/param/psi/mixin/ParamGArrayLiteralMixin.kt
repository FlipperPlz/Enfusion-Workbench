package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.ParamFile
import com.flipperplz.bisutils.param.node.ParamElement
import com.flipperplz.bisutils.param.node.ParamLiteralBase
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGArrayLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGArrayElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGLiteralImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.parentOfType

abstract class ParamGArrayLiteralMixin(node: ASTNode) : ParamGArrayElementImpl(node), ParamGArrayLiteral {
    override val slimValue: List<ParamLiteralBase>
        get() = literalList
}