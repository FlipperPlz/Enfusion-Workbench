package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement
import com.intellij.lang.ASTNode

class ParamStatementImpl(node: ASTNode) : ParamStatement, ParamCompositeElementImpl(node)