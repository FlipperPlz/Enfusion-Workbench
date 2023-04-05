package com.flipperplz.enfusionWorkbench.languages.param.lexer

import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamLexerState
import com.flipperplz.enfusionWorkbench.psi.lexer.EnfusionFlexLexerAdapter

class ParamLexerAdapter : EnfusionFlexLexerAdapter<ParamLexerState>(ParamLexer())