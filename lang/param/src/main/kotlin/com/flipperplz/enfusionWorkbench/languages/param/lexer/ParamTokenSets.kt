package com.flipperplz.enfusionWorkbench.languages.param.lexer

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.psi.tree.TokenSet

object ParamTokenSets {
    val STRINGS: TokenSet = TokenSet.create(ParamTypes.STRING_LITERAL)
    val IDENTIFIERS: TokenSet = TokenSet.create(ParamTypes.IDENTIFIER)
    val COMMENTS: TokenSet = TokenSet.create(ParamTypes.DELIMITED_COMMENT, ParamTypes.EMPTY_DELIMITED_COMMENT, ParamTypes.SINGLE_LINE_COMMENT)
}