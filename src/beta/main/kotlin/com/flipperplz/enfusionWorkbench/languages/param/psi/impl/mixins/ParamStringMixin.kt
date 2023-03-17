package com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins

import com.flipperplz.enfusionWorkbench.languages.param.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamString
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamLiteralImpl
import com.intellij.lang.ASTNode

abstract class ParamStringMixin(node: ASTNode) : ParamLiteralImpl(node), ParamString {
    override fun asKtString(): String = if(quoted)
        text.removePrefix("\"").removeSuffix("\"").replace("\"\"", "\"") else
        text

    override var quoted: Boolean
        get() = text.startsWith("\"") && text.endsWith("\"")
        set(value) {
            if(quoted == value) return
            if(quoted) replace(ParamElementFactory.createString(project, text.replace("\"\"","\"").removeSuffix("\"").removePrefix("\"")))
            else replace(ParamElementFactory.createString(project, "\"${text.replace("\"", "\"\"")}\""))
        }
}