package com.flipperplz.enfusionWorkbench.psi

import com.intellij.openapi.fileTypes.LanguageFileType

abstract class EnfusionLanguageFileType(
    language: EnfusionLanguage
) : LanguageFileType(
    language
)