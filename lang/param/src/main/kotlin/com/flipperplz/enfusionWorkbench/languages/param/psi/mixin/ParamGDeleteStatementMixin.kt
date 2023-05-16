package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.ParamFile
import com.flipperplz.bisutils.param.node.ParamElement
import com.flipperplz.bisutils.param.statement.ParamExternalClass
import com.flipperplz.bisutils.param.utils.extensions.ParamSlimUtils.findElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGDeleteStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType

abstract class ParamGDeleteStatementMixin(node: ASTNode) : ParamGStatementImpl(node), ParamGDeleteStatement {
    override fun shouldValidateTarget(): Boolean = false

    override fun locateTargetClass(): ParamExternalClass? = if(!slimName.isNullOrBlank() && containingParamFile != null)
        containingParamFile?.findElement(slimName!!) as ParamExternalClass
    else null

    override val slimName: String?
        get() = identifier?.name
}