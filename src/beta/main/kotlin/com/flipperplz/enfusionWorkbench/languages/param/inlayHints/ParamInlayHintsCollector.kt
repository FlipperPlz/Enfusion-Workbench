@file:Suppress("UnstableApiUsage")

package com.flipperplz.enfusionWorkbench.languages.param.inlayHints

import com.intellij.codeInsight.hints.FactoryInlayHintsCollector
import com.intellij.codeInsight.hints.InlayHintsSink
import com.intellij.codeInsight.hints.SettingsKey
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class ParamInlayHintsCollector(
    editor: Editor,
    private val file: PsiFile,
    private val settings: ParamInlayHintsProvider.Settings,
    private val sink: InlayHintsSink,
    private val key: SettingsKey<ParamInlayHintsProvider.Settings>,
) : FactoryInlayHintsCollector(editor){
    override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
        TODO("Not yet implemented")
    }

}
