package com.flipperplz.bisutils.languages.param.psi

import com.intellij.psi.PsiFile
import com.intellij.psi.util.childrenOfType

interface ParamFile : PsiFile, ParamPsiCommandsHolder {
    override val commands: List<ParamCommand>
        get() = childrenOfType()
}