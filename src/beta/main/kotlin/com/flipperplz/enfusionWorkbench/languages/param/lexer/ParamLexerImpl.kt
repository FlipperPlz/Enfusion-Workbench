package com.flipperplz.enfusionWorkbench.languages.param.lexer

import com.flipperplz.enfusionWorkbench.languages.param.lexer.states.ParamLexerState
import com.flipperplz.enfusionWorkbench.psi.lexer.EnfusionFlexLexer

interface ParamLexerImpl : EnfusionFlexLexer<ParamLexerState>;