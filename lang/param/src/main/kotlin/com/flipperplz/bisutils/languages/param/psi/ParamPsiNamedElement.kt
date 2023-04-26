package com.flipperplz.bisutils.languages.param.psi

import com.flipperplz.bisutils.languages.param.parser.ParamElementType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.childrenOfType

interface ParamPsiNamedElement : PsiNameIdentifierOwner {

    override fun getNameIdentifier(): ParamIdentifier? = childrenOfType<ParamIdentifier>().firstOrNull()

    override fun setName(name: String): PsiElement = nameIdentifier?.setName(name) ?: this
}