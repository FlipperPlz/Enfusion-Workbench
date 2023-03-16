package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamClassContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamIdentifierContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamCompositeElementImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.childrenOfType
import com.jetbrains.rd.util.remove

open class ParamClass(node: ASTNode) : ParamCompositeElementImpl(node), ParamClassContext {

    override val previousScope: ParamClassContext = parent as ParamClassContext

    override val isExternal: Boolean
        get() = (!text.contains('{') && childStatements.isEmpty() && superClass == null)

    override val superClass: ParamClassContext?
        get() {
            val identifiers = childStatements.filterIsInstance<ParamIdentifierContext>()
            return if(identifiers.count() > 1) {
                previousScope.getClassByName(true, identifiers[1].identifierName)
            } else null
        }

    override val className: ParamIdentifier
        get() = children.first { it is ParamIdentifier} as ParamIdentifier

    override val childStatements: List<ParamStatement>
        get() = children.filterIsInstance<ParamStatement>()
}