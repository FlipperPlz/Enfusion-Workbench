package com.flipperplz.enfusionWorkbench.languages

import com.intellij.openapi.fileTypes.LanguageFileType

abstract class EnfusionLanguageFileType(
    language: EnfusionLanguage
) : LanguageFileType(
    language
)