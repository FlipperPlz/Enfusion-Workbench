package com.flipperplz.enfusionWorkbench.languages.param.psi.required

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.psi.tree.TokenSet

interface ParamTokenSets {
    companion object {
        val IDENTIFIERS: TokenSet = TokenSet.create(ParamTypes.ABS_IDENTIFIER)
        val STRINGS: TokenSet = TokenSet.create(ParamTypes.ABS_STRING)
        val NUMBERS: TokenSet = TokenSet.create(ParamTypes.ABS_NUMERIC)
        val COMMENTS: TokenSet = TokenSet.create(ParamTypes.DELIMITED_COMMENT, ParamTypes.EMPTY_DELIMITED_COMMENT, ParamTypes.SINGLE_LINE_COMMENT)

    }
}