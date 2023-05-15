package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.utils.ParamStringType
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamGStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamGLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamGStringLiteralMixin(node: ASTNode) : ParamGLiteralImpl(node), ParamGStringLiteral {
    override val slimValue: String
        get() = slimStringType.destringify(text)
    override val slimStringType: ParamStringType
        get() = if(text.startsWith('"')) ParamStringType.QUOTED else ParamStringType.UNQUOTED
}