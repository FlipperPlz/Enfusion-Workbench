package com.flipperplz.enfusionWorkbench.languages.param.lexer.states

import com.flipperplz.enfusionWorkbench.languages.param.lexer.ParamLexer
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.flipperplz.enfusionWorkbench.psi.lexer.states.EnfusionFlexLexerState
import com.flipperplz.enfusionWorkbench.utils.EnfLoggableObject
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class ParamLexerState(
    lexer: ParamLexer,
    initialState: Int,
    private val stringStateId: Int,
    private val localizationStateId: Int
) : EnfusionFlexLexerState(
    lexer,
    initialState
) {
    companion object : EnfLoggableObject(this::class.java)

    private var stringType: ParamStringType = ParamStringType.NOT_STRING
    private var currentBraceLevel: Int = 0

    fun currentBraceLevel(): Int = this.currentBraceLevel;

    fun popBraceLevel(): Int = this.currentBraceLevel--;

    fun pushBraceLevel(): Int = this.currentBraceLevel++;

    fun exitStringMode(): IElementType = if(this.stringType == ParamStringType.NOT_STRING || !assertState(this.stringStateId)) {
        TokenType.BAD_CHARACTER
    } else this.stringType.endToken.also {
        this.stringType = ParamStringType.NOT_STRING
        popState()
    }!!

    fun enterStringMode(type: ParamStringType): IElementType {
        if(type == ParamStringType.NOT_STRING) return exitStringMode();
        if(this.stringType != ParamStringType.NOT_STRING  || assertState(this.stringStateId))
            return TokenType.BAD_CHARACTER
        this.stringType = type
        pushState(this.stringStateId)

        return type.startToken!!;
    }

    fun handleStringEnd(type: ParamStringType): IElementType = when(type) {
        ParamStringType.DOUBLE -> if (this.stringType != ParamStringType.DOUBLE) ParamTypes.STRING_CONTENT else ParamTypes.STRING_END
        ParamStringType.SINGLE -> if (this.stringType != ParamStringType.SINGLE) ParamTypes.STRING_CONTENT else ParamTypes.STRING_END
        ParamStringType.INCLUDE -> if(this.stringType != ParamStringType.INCLUDE) ParamTypes.STRING_CONTENT else ParamTypes.INCLUDE_END
        ParamStringType.NONE -> if(this.stringType == ParamStringType.NONE) ParamTypes.STRING_END else ParamTypes.STRING_CONTENT
        ParamStringType.NOT_STRING -> if(this.stringType == ParamStringType.NONE) ParamTypes.STRING_END else TokenType.BAD_CHARACTER
        else -> TokenType.BAD_CHARACTER
    }

    fun exitLocalizationMode(): IElementType = if(this.stringType != ParamStringType.NOT_STRING  || !assertState(this.localizationStateId))
        TokenType.BAD_CHARACTER else popStateAndReturn(ParamTypes.LOCALIZED_STRING)


    fun handleLocalizationMode(): Boolean {
        if(this.stringType == ParamStringType.INCLUDE)
            return false

        enterLocalizationMode()
        return true
    }



    private fun enterLocalizationMode() = pushState(this.localizationStateId)
}