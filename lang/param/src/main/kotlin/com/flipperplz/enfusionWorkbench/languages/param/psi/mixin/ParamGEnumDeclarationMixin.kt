package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.ParamFile
import com.flipperplz.bisutils.param.node.ParamElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGEnumDeclaration
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGEnumValue
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGStatementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType

abstract class ParamGEnumDeclarationMixin(node: ASTNode) : ParamGStatementImpl(node), ParamGEnumDeclaration {
    override val enumValues: Map<String, Int>?
        get() = TODO() //enumValueList.map { it.identifier.name to it.numericLiteral }
}