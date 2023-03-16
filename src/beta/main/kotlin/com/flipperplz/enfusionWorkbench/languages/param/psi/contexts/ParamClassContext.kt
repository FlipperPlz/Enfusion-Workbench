package com.flipperplz.enfusionWorkbench.languages.param.psi.contexts

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier

interface ParamClassContext : ParamStatement {
    val previousScope: ParamClassContext?;

    val className: ParamIdentifier
    val superClass: ParamClassContext?
    val isExternal: Boolean

    val childStatements: List<ParamStatement>

    fun getClasses(): List<ParamClassContext> = childStatements.filterIsInstance<ParamClassContext>()

    fun getExternalClasses() = getClasses().filter { it.isExternal }

    fun getClassByName(isExternalClass: Boolean = false, className: String? ): ParamClassContext? =
        if(className == null) null else (if(isExternalClass) getExternalClasses() else getClasses()).firstOrNull() {
            it.className.identifierName == className.toLowerCase()
        }

}