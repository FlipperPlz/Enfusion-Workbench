package com.flipperplz.enfusionWorkbench.languages.param.psi.impl

import com.flipperplz.enfusionWorkbench.languages.EnfusionLanguageFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.mixins.ParamNonBinaraizableMixin
import com.flipperplz.enfusionWorkbench.psi.impl.EnfusionPsiFileImpl
import com.intellij.psi.FileViewProvider

class ParamFileImpl(
    viewProvider: FileViewProvider
) : EnfusionPsiFileImpl(viewProvider, ParamLanguage) {

    override val enfusionFileType: EnfusionLanguageFileType = ParamFileType.instance

    override val binarizable: Boolean
        get() = children.any { it is ParamNonBinaraizableMixin }
}