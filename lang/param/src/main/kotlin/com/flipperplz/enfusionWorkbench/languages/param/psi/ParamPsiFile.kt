package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType

interface ParamPsiFile : PsiFile, ParamPsiCommandsHolder {
    override val commands: List<ParamCommand>
        get() = childrenOfType()
}