package com.flipperplz.enfusionWorkbench.psi.lexer

import com.flipperplz.enfusionWorkbench.psi.lexer.states.EnfusionFlexLexerState
import com.flipperplz.enfusionWorkbench.psi.lexer.states.EnfusionLexerState
import com.intellij.lexer.FlexLexer

interface EnfusionFlexLexer<State: EnfusionFlexLexerState> : FlexLexer {
    val lexerState: State;
}