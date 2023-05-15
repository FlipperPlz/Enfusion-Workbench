package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.bisutils.param.ParamFile
import com.flipperplz.bisutils.param.node.ParamElement
import com.flipperplz.bisutils.param.utils.ParamElementTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType

interface ParamPsiElement : PsiElement, ParamElement {
    override val slimParent: ParamElement?
        get() = parentOfType<ParamPsiElement>()
    override val containingParamFile: ParamFile?
        get() = containingFile as? ParamFile?
}