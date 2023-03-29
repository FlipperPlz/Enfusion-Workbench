package com.flipperplz.enfusionWorkbench.psi.lexer.states

import com.intellij.lexer.FlexLexer
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.flipperplz.enfusionWorkbench.utils.EnfLoggableObject;

open class EnfusionFlexLexerState(
    lexer: FlexLexer,
    private var initialState: Int,
) : EnfusionLexerState<FlexLexer>(lexer) {
    private val stateDeque = ArrayDeque<Int>(elements = listOf(initialState))
    private var previousState: Int = initialState
    companion object : EnfLoggableObject(EnfusionFlexLexerState::class.java)

    fun popState(): Int {
        if (stateDeque.size <= 1) ENF_LOGGER.error(IllegalStateException("Cannot pop initial state"))
        stateDeque.removeLast()
        previousState = stateDeque.last()
        lexer.yybegin(previousState)
        return previousState
    }

    fun pushState(id: Int): Int = with(id) {
        if(assertState(this)) return id

        stateDeque.addLast(id)
        previousState = stateDeque.elementAt(stateDeque.lastIndex - 1)
        lexer.yybegin(id)
        return id
    }

    fun popStateAndReturn(ret: IElementType): IElementType {
        popState()
        return ret;
    }

    fun popStateAndReturn(ret: IElementType, popCount: Int): IElementType {
        repeat(popCount) { popState() }
        return ret;
    }

    fun pushStateAndReturn(id: Int, ret: IElementType): IElementType = pushStateAndReturn<IElementType>(id, ret)

    fun popStateAndReturnBad(): IElementType = popStateAndReturn(TokenType.BAD_CHARACTER)

    inline fun popStateAndReturn(ret: () -> IElementType): IElementType = popStateAndReturn<IElementType>(ret)

    inline fun pushStateAndReturn(id: Int, ret: () -> IElementType): IElementType = pushStateAndReturn<IElementType>(id, ret)

    inline fun <T> pushStateAndReturn(id: Int, ret: () -> T): T {
        pushState(id)
        return ret.invoke();
    }

    inline fun <T> popStateAndReturn(ret: () -> T): T {
        popState()
        return ret.invoke();
    }

    fun <T> pushStateAndReturn(id: Int, ret: T): T {
        pushState(id)
        return ret;
    }

    protected fun assertState(id: Int): Boolean = with(stateDeque.last()) {
        this == id ||this == lexer.yystate()
    }
}
