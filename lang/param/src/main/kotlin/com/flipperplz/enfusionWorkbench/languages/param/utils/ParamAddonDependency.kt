package com.flipperplz.enfusionWorkbench.languages.param.utils

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile

data class ParamAddonDependency(
    val preloaded: Boolean,
    val addon: ParamPsiFile,
    val dependsOn: MutableList<ParamPsiFile>
)