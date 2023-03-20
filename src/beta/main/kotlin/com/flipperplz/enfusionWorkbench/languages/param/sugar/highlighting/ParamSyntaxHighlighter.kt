package com.flipperplz.enfusionWorkbench.languages.param.sugar.highlighting

import com.flipperplz.enfusionWorkbench.languages.param.lexer.ParamLexerAdapter
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType


class ParamSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        private val OPERATOR: TextAttributesKey = createTextAttributesKey("PARAM_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEYWORD: TextAttributesKey = createTextAttributesKey("PARAM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING: TextAttributesKey = createTextAttributesKey("PARAM_STRING", DefaultLanguageHighlighterColors.STRING)
        val LINE_COMMENT: TextAttributesKey = createTextAttributesKey("PARAM_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val DELIMITED_COMMENT: TextAttributesKey = createTextAttributesKey("PARAM_DELIMITED_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val NUMBER: TextAttributesKey = createTextAttributesKey("PARAM_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val SEMICOLON: TextAttributesKey = createTextAttributesKey("PARAM_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val COMMA: TextAttributesKey = createTextAttributesKey("PARAM_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val BRACES: TextAttributesKey = createTextAttributesKey("PARAM_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS: TextAttributesKey = createTextAttributesKey("PARAM_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val IDENTIFIER: TextAttributesKey = createTextAttributesKey("PARAM_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)

        val BAD_CHARACTER: TextAttributesKey =
            createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)


        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val BLOCK_COMMENT_KEYS = arrayOf(DELIMITED_COMMENT)
        private val SEMICOLON_KEYS = arrayOf(SEMICOLON)
        private val BRACKETS_KEYS = arrayOf(BRACKETS)
        private val STRING_KEYS = arrayOf(STRING)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()

    }

    override fun getHighlightingLexer(): Lexer = ParamLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when(tokenType) {
        ParamTypes.DELIMITED_COMMENT -> BLOCK_COMMENT_KEYS
        ParamTypes.EMPTY_DELIMITED_COMMENT -> BLOCK_COMMENT_KEYS
        ParamTypes.SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
        ParamTypes.OP_ASSIGN -> OPERATOR_KEYS
        ParamTypes.OP_ADDASSIGN -> OPERATOR_KEYS
        ParamTypes.OP_SUBASSIGN -> OPERATOR_KEYS
        ParamTypes.KW_CLASS -> KEYWORD_KEYS
        ParamTypes.KW_ENUM -> KEYWORD_KEYS
        ParamTypes.KW_DELETE -> KEYWORD_KEYS
        ParamTypes.ABS_STRING -> STRING_KEYS
        ParamTypes.ABS_IDENTIFIER -> IDENTIFIER_KEYS
        ParamTypes.SYM_COMMA -> COMMA_KEYS
        ParamTypes.SYM_LCURLY -> BRACES_KEYS
        ParamTypes.SYM_RCURLY -> BRACES_KEYS
        ParamTypes.SYM_LSQUARE -> BRACKETS_KEYS
        ParamTypes.SYM_RSQUARE -> BRACKETS_KEYS
        ParamTypes.ABS_NUMERIC -> NUMBER_KEYS
        ParamTypes.SYM_SEMI -> SEMICOLON_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

}