package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNamedPsiElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamClassMixin
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class ParamClassDeclarationMixinImpl(
    node: ASTNode
) : ParamPsiElementImpl(
    node
), ParamClassMixin {

    override fun getNameIdentifier(): ParamNamedPsiElement? = identifierList.firstOrNull()?.nameIdentifier as ParamNamedPsiElement

    override fun getName(): String? = nameIdentifier?.text

    override fun setName(name: String): PsiElement? = nameIdentifier?.setName(name)

    override var classname: String?
        get() = name
        set(value) {setName(value ?: "undefined")}

    override val binarizable: Boolean get() = statementList.all { it.binarizable }

    override val isExternalClass: Boolean get() = symLcurly == null && symColon == null;
}