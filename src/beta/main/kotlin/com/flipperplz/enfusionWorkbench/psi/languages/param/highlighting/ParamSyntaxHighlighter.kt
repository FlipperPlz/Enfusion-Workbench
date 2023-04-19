package com.flipperplz.enfusionWorkbench.psi.languages.param.highlighting

import com.flipperplz.enfusionWorkbench.psi.languages.param.lexer.ParamLexerAdapter
import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.ParamTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

@Suppress("MemberVisibilityCanBePrivate")
class ParamSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        val OPERATOR: TextAttributesKey = createTextAttributesKey("PARAM_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEYWORD: TextAttributesKey = createTextAttributesKey("PARAM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING: TextAttributesKey = createTextAttributesKey("PARAM_STRING", DefaultLanguageHighlighterColors.STRING)
        val STRING_ESCAPE: TextAttributesKey = createTextAttributesKey("PARAM_ESCAPE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
        val LOCALIZED_STRING: TextAttributesKey = createTextAttributesKey("PARAM_LOCALIZATION", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
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
        private val STRING_ESCAPES = arrayOf(STRING_ESCAPE)
        private val LOCALIZED_STRINGS = arrayOf(LOCALIZED_STRING)


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
        ParamTypes.PROCEDURAL_TEXTURE -> LOCALIZED_STRINGS
        ParamTypes.STRING_AMBIGUOUS_START -> STRING_KEYS
        ParamTypes.STRING_SINGLE_START -> STRING_KEYS
        ParamTypes.STRING_DOUBLE_START -> STRING_KEYS
        ParamTypes.STRING_INCLUDE_START -> STRING_KEYS
        ParamTypes.STRING_AMBIGUOUS_END -> STRING_KEYS
        ParamTypes.STRING_DOUBLE_END -> STRING_KEYS
        ParamTypes.STRING_SINGLE_END -> STRING_KEYS
        ParamTypes.STRING_INCLUDE_END -> STRING_KEYS
        ParamTypes.STRING_CONTENT -> STRING_KEYS
        ParamTypes.STRING_ESCAPE -> STRING_ESCAPES
        ParamTypes.DEFINE_VALUE -> STRING_KEYS
        ParamTypes.LOCALIZED_STRING -> LOCALIZED_STRINGS
        ParamTypes.ABS_IDENTIFIER -> IDENTIFIER_KEYS
        ParamTypes.MACRO_SIGNATURE -> IDENTIFIER_KEYS
        ParamTypes.SYM_COMMA -> COMMA_KEYS
        ParamTypes.SYM_LCURLY -> BRACES_KEYS
        ParamTypes.SYM_RCURLY -> BRACES_KEYS
        ParamTypes.SYM_LSQUARE -> BRACKETS_KEYS
        ParamTypes.SYM_RSQUARE -> BRACKETS_KEYS
        ParamTypes.DIRECTIVE_START -> LOCALIZED_STRINGS
        ParamTypes.ABS_NUMERIC -> NUMBER_KEYS
        ParamTypes.SYM_SEMI -> SEMICOLON_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

}