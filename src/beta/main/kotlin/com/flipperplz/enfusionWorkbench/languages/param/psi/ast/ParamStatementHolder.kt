package com.flipperplz.enfusionWorkbench.languages.param.psi.ast

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement

interface ParamStatementHolder : ParamScopeComponent {
    val statements: List<ParamStatement>
    override val previousScope: ParamStatementHolder?
    override val childScopes: List<ParamStatementHolder>

    fun locateClassInScope(className: ParamIdentifier, externalOnly: Boolean = false): ParamClass? =
        locateClassInScope(className.identifierName)


    fun locateClassInScope(className: String, externalOnly: Boolean = false): ParamClass? =
        statements.firstOrNull {
            it is ParamClass &&
                    it.name.equals(className, ignoreCase = true) &&
                    !(externalOnly && it.isExternal)
        } as ParamClass?
}