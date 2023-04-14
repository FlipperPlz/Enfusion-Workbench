@file:Suppress("UnstableApiUsage")

package com.flipperplz.enfusionWorkbench.languages.param.inlayHints

import com.intellij.codeInsight.hints.*
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import javax.swing.JPanel

class ParamInlayHintsProvider : InlayHintsProvider<ParamInlayHintsProvider.Settings> {

    data class Settings(var showForTextures: Boolean = true)
    override val key: SettingsKey<Settings>
        get() = KEY
    override val name: String
        get() = "ParamFile Hints"
    override val previewText: String? = null

    override fun createSettings(): Settings = Settings()
    override fun getCollectorFor(
        file: PsiFile,
        editor: Editor,
        settings: Settings,
        sink: InlayHintsSink
    ): InlayHintsCollector = ParamInlayHintsCollector(editor, file, settings, sink, KEY)


    override fun createConfigurable(settings: Settings) = object : ImmediateConfigurable {
        override val mainCheckboxText = "Use inline hints for ParamFiles"

        override val cases = listOf(
            ImmediateConfigurable.Case("Show for textures", "textures", settings::showForTextures)
        )

        override fun createComponent(listener: ChangeListener) = JPanel()
    }

    companion object {
        private val KEY: SettingsKey<Settings> = SettingsKey("paramlang.hints")
    }
}