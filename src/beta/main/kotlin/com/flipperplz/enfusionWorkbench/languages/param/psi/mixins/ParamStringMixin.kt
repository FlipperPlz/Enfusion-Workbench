package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStringLiteralImpl
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType
import com.intellij.lang.ASTNode

abstract class ParamStringMixin(node: ASTNode) : ParamStringLiteral, ParamPsiElementImpl(node) {

    val stringType: ParamStringType
        get() = ParamStringType.detectStringType(this)

    val stringText: String
        get() = stringType.prune(stringContent)

}