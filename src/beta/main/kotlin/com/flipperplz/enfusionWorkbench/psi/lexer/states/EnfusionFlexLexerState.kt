package com.flipperplz.enfusionWorkbench.psi.lexer.states

import com.intellij.lexer.FlexLexer
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.flipperplz.enfusionWorkbench.utils.EnfLoggableObject;
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

open class EnfusionFlexLexerState(
    lexer: FlexLexer,
    private var initialState: Int,
) : EnfusionLexerState<FlexLexer>(lexer) {
    companion object : EnfLoggableObject(EnfusionFlexLexerState::class.java)

    private val stateDeque = ArrayDeque<Int>(elements = listOf(initialState))
    private var previousState: Int = initialState
    private var currentState: Int by Delegates.observable(initialState, ::onStateUpdated)


    fun popState(): Int {
        if (stateDeque.size <= 1) ENF_LOGGER.error(IllegalStateException("Cannot pop initial state"))
        stateDeque.removeLast()
        currentState = stateDeque.last()
        previousState = stateDeque.elementAtOrNull(stateDeque.lastIndex - 1) ?: initialState

        return currentState
    }

    fun pushState(id: Int): Int {
        previousState = currentState
        currentState = id

        return currentState
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

    private fun onStateUpdated(property: KProperty<*>, oldState: Int, newState: Int) = lexer.yybegin(newState);

    protected fun assertState(id: Int): Boolean = with(stateDeque.last()) { this == lexer.yystate() }
}
