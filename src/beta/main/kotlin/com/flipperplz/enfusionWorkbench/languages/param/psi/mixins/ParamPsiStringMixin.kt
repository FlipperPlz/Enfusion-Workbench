package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamString
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

abstract class ParamPsiStringMixin(node: ASTNode) : ParamString, ParamCompositeElementImpl(node) {

    override fun writeAsParam(): String = "{${toString().replace("\"", "\"\"")}\""
    override fun toString(): String = quotationType?.asUnquotedEscaped(text) ?: text


}