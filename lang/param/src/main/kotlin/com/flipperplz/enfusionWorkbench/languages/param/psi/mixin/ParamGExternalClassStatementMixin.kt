package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.ParamFile
import com.flipperplz.bisutils.param.node.ParamElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGExternalClassStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.parentOfType

abstract class ParamGExternalClassStatementMixin(node: ASTNode) : ParamGStatementImpl(node), ParamGExternalClassStatement {

    override val slimName: String?
        get() = identifier?.name
}