package com.flipperplz.enfusionWorkbench.psi.sets

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamTypes
import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet

object ParamTokenSets {
    val IDENTIFIERS: TokenSet = TokenSet.create(ParamTypes.ABS_IDENTIFIER)
    val STRINGS: TokenSet = TokenSet.create(ParamTypes.STRING_LITERAL, ParamTypes.INCLUDE_STRING)
    val NUMBERS: TokenSet = TokenSet.create(ParamTypes.ABS_NUMERIC)
    val COMMENTS: TokenSet = TokenSet.create(ParamTypes.DELIMITED_COMMENT, ParamTypes.EMPTY_DELIMITED_COMMENT, ParamTypes.SINGLE_LINE_COMMENT)
    val OPERATORS: TokenSet = TokenSet.create(ParamTypes.OP_ASSIGN, ParamTypes.OP_ADDASSIGN, ParamTypes.OP_SUBASSIGN)
    val CURLIES: TokenSet = TokenSet.create(ParamTypes.SYM_LCURLY, ParamTypes.SYM_RCURLY)
    val WHITESPACES: TokenSet = TokenSet.create(ParamTypes.EOL, TokenType.WHITE_SPACE)

    val BLOCK_RULES: TokenSet = TokenSet.create(ParamTypes.ARRAY_LITERAL, ParamTypes.CLASS_STATEMENT, ParamTypes.ENUM_DECLARATION)

}