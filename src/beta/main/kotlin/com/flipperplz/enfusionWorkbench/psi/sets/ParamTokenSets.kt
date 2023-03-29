package com.flipperplz.enfusionWorkbench.psi.sets

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.psi.tree.TokenSet

object ParamTokenSets {
    val WHITESPACES: TokenSet = TokenSet.create(ParamTypes.WHITE_SPACE)
    val IDENTIFIERS: TokenSet = TokenSet.create(ParamTypes.ABS_IDENTIFIER)
    val STRINGS: TokenSet = TokenSet.create(ParamTypes.ABS_STRING)
    val NUMBERS: TokenSet = TokenSet.create(ParamTypes.ABS_NUMERIC)
    val COMMENTS: TokenSet = TokenSet.create(ParamTypes.DELIMITED_COMMENT, ParamTypes.EMPTY_DELIMITED_COMMENT, ParamTypes.SINGLE_LINE_COMMENT)
    val OPERATORS: TokenSet = TokenSet.create(ParamTypes.OP_ASSIGN, ParamTypes.OP_ADDASSIGN, ParamTypes.OP_SUBASSIGN)
    val CURLIES: TokenSet = TokenSet.create(ParamTypes.SYM_LCURLY, ParamTypes.SYM_RCURLY)

    val BLOCK_RULES: TokenSet = TokenSet.create(ParamTypes.ARRAY, ParamTypes.CLASS_DECLARATION)

}