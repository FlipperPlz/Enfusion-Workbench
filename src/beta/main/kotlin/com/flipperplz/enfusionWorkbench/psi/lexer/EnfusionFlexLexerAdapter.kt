package com.flipperplz.enfusionWorkbench.psi.lexer

import com.flipperplz.enfusionWorkbench.psi.lexer.states.EnfusionFlexLexerState
import com.intellij.lexer.FlexAdapter

abstract class EnfusionFlexLexerAdapter<T : EnfusionFlexLexerState>(
    val enfusionLexer: EnfusionFlexLexer<T>
) : FlexAdapter(enfusionLexer) {
    val enfusionLexerState: T
        get() = enfusionLexer.lexerState
}