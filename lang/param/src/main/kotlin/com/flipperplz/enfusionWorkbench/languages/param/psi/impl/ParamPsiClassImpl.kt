package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.bisutils.param.slim.ParamSlimClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiCommandsHolder
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType
import com.intellij.psi.util.parentOfType

open class ParamPsiClassImpl(node: ASTNode) : ParamExternalClassStatementImpl(node), ParamPsiClass {
    override val isExternalParamClass: Boolean = false

    override fun getNameIdentifier(): ParamIdentifier? = identifier

    override var className: String
        get() = nameIdentifier?.name ?: ""
        set(value) { nameIdentifier?.setName(value) }



    override val commands: List<ParamCommand>
        get() = childrenOfType()

    override val paramSuperClass: ParamIdentifier?
        get() = findChildByType(ParamTypes.IDENTIFIER)

    override fun toEnforce(): String = super<ParamPsiClass>.toEnforce()

    override val parentCommandsHolder: ParamPsiCommandsHolder?
        get() = parentOfType()



}