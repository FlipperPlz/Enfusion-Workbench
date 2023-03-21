package com.flipperplz.enfusionWorkbench.languages.param.sugar.codeStyle.panels

import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleSettings

class ParamCodeStyleMainPanel(
    currentSettings: CodeStyleSettings?,
    settings: CodeStyleSettings?
) : TabbedLanguageCodeStylePanel(
    ParamLanguage,
    currentSettings,
    settings
) {

}