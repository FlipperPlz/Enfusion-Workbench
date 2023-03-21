package com.flipperplz.enfusionWorkbench.languages.param.sugar.codeStyle

import com.flipperplz.enfusionWorkbench.languages.param.sugar.codeStyle.panels.ParamCodeStyleMainPanel
import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CustomCodeStyleSettings

class ParamCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
    override fun createCustomSettings(settings: CodeStyleSettings?): CustomCodeStyleSettings = ParamCodeStyleSettings(settings)

    override fun getConfigurableDisplayName(): String = "Param"

    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable = object : CodeStyleAbstractConfigurable(settings, modelSettings, this.configurableDisplayName) {
        override fun createPanel(settings: CodeStyleSettings?): CodeStyleAbstractPanel = ParamCodeStyleMainPanel(currentSettings, settings)
    }
}