package com.flipperplz.enfusionWorkbench.languages.param.utils

import com.flipperplz.enfusionWorkbench.languages.param.lexer.ParamLexer
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.flipperplz.enfusionWorkbench.psi.lexer.states.EnfusionFlexLexerState
import com.flipperplz.enfusionWorkbench.utils.EnfLoggableObject
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ParamLexerState(
    lexer: ParamLexer,
    initialState: Int,
    private val stringStateId: Int,
) : EnfusionFlexLexerState(
    lexer,
    initialState
) {
    companion object : EnfLoggableObject(this::class.java)

    private var stringType: ParamStringType by Delegates.observable(ParamStringType.NOT_STRING, ::onStringStateUpdated)

    private var currentBraceLevel: Int = 0

    fun currentBraceLevel(): Int = this.currentBraceLevel

    fun popBraceLevel(): Int = this.currentBraceLevel--

    fun pushBraceLevel(): Int = this.currentBraceLevel++

    fun enterLeftCurly(): IElementType {
        pushBraceLevel()
        return ParamTypes.SYM_LCURLY
    }

    fun enterRightCurly(): IElementType {
        pushBraceLevel()
        return ParamTypes.SYM_RCURLY
    }

    fun enterStringMode(type: ParamStringType): IElementType {
        if(stringType == type || type == ParamStringType.NOT_STRING) return handleStringEnd(type)
        if(this.stringType != ParamStringType.NOT_STRING) return TokenType.BAD_CHARACTER
        this.stringType = type

        return type.startToken!!
    }

    fun handleStringEnd(type: ParamStringType): IElementType = when(type) {
        ParamStringType.DOUBLE -> if (this.stringType != ParamStringType.DOUBLE) ParamTypes.STRING_CONTENT else exitStringMode()
        ParamStringType.SINGLE -> if (this.stringType != ParamStringType.SINGLE) ParamTypes.STRING_CONTENT else exitStringMode()
        ParamStringType.INCLUDE -> if(this.stringType != ParamStringType.INCLUDE) ParamTypes.STRING_CONTENT else exitStringMode()
        ParamStringType.NONE -> if(this.stringType == ParamStringType.NONE) exitStringMode() else ParamTypes.STRING_CONTENT
        ParamStringType.NOT_STRING -> if(this.stringType == ParamStringType.NONE) exitStringMode() else TokenType.BAD_CHARACTER
    }

    private fun exitStringMode(): IElementType = if(this.stringType == ParamStringType.NOT_STRING) {
        TokenType.BAD_CHARACTER
    } else {
        val end = this.stringType.endToken
        this.stringType = ParamStringType.NOT_STRING

        end
    }!!

    private fun onStringStateUpdated(kProperty: KProperty<*>, oldType: ParamStringType, newType: ParamStringType) {
        if(oldType == newType) return
        if(oldType != ParamStringType.NOT_STRING) {
            popState()
        } else pushState(stringStateId)
    }

}