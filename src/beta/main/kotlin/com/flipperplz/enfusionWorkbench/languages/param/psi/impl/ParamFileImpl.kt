package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.interfaces.ParamPsiClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamNonBinaraizableMixin
import com.flipperplz.enfusionWorkbench.psi.impl.EnfusionPsiFileImpl
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.childrenOfType

class ParamFileImpl(
    viewProvider: FileViewProvider,
    override val isExternal: Boolean = false
) : EnfusionPsiFileImpl(viewProvider, ParamLanguage), ParamPsiClass {

    override val enfusionFileType: EnfusionLanguageFileType = ParamFileType.instance

    override val classnameIdentifier: ParamIdentifier = ParamElementFactory.createIdentifier(project, virtualFile.nameWithoutExtension)
    override fun getNameIdentifier(): PsiElement = classnameIdentifier

    override val parentClassnameIdentifier: ParamIdentifier? = null
    override val statements: List<ParamStatement>
        get() = childrenOfType()

    override val binarizable: Boolean
        get() = children.any { it is ParamNonBinaraizableMixin }
}