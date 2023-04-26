package com.flipperplz.bisutils.languages.param.psi

import com.intellij.psi.PsiElement

interface ParamPsiStatementsHolder : PsiElement {
    val statements: List<ParamStatement>
}