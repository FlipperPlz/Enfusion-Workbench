package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.bisutils.param.node.RapFile
import com.flipperplz.bisutils.param.node.RapStatement
import com.flipperplz.bisutils.param.node.RapStatementHolder
import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType

interface ParamPsiFile : PsiFile, RapFile {
    override val slimCommands: List<ParamStatement>
        get() = childrenOfType<ParamStatement>()
}