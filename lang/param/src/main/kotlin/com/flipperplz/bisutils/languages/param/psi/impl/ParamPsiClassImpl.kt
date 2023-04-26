package com.flipperplz.bisutils.languages.param.psi.impl

import com.flipperplz.bisutils.languages.param.psi.ParamCommand
import com.flipperplz.bisutils.languages.param.psi.ParamIdentifier
import com.flipperplz.bisutils.languages.param.psi.ParamPsiClass
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType

open class ParamPsiClassImpl(node: ASTNode) : ParamExternalClassStatementImpl(node), ParamPsiClass {
    override val className: String? = name
    override val isExternalParamClass: Boolean = false
    override val commands: List<ParamCommand> = childrenOfType()
    override val paramSuperClass: ParamIdentifier?
        get() = childrenOfType<ParamIdentifier>().getOrNull(1)

    override fun getNameIdentifier(): ParamIdentifier? = identifier

}