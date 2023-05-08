package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.bisutils.param.slim.ParamSlimCommand
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType

interface ParamPsiCommand : ParamSlimCommand, PsiElement  {

    override fun toEnforce(): String = "//undefined"
}