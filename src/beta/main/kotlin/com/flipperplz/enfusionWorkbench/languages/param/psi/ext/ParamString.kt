package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamStringLiteralContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode

class ParamString(node: ASTNode) : ParamCompositeElementImpl(node), ParamStringLiteralContext {
    override fun asKotlinString(returnQuoted: Boolean): String {
        val string = text.removePrefix("\"").removeSuffix("\"").replace("\"\"", "\"")
        return if(returnQuoted) "\"${string.replace("\"", "\"\"")}\"" else string
    }
}