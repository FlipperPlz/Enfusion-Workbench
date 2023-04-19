package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.psi.languages.param.utils.ParamStringType
import com.intellij.lang.ASTNode

abstract class ParamStringMixin(node: ASTNode) : ParamStringLiteral, ParamPsiElementImpl(node) {

    val stringType: ParamStringType
        get() = ParamStringType.detectStringType(this)

    val stringText: String
        get() = stringType.prune(stringContent)

}