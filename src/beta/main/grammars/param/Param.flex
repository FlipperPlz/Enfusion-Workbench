package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamLexerState;import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;

import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;

%%

%{
    private final @NotNull ParamLexerState currentState = new ParamLexerState(
            this,
            this.YYINITIAL,
            this.STRING_MODE
        );

        public ParamLexer() { this((java.io.Reader)null); }

        @Override
        public @NotNull ParamLexerState getLexerState() { return this.currentState; }
%}

%public
%class ParamLexer
%implements EnfusionFlexLexer<ParamLexerState>
%function advance
%type IElementType
%unicode

LINE_TERMINATOR=\r?\n
WHITE_SPACES=[\s\t\r\n]

SPACE=\s

SINGLE_LINE_COMMENT="//".*{LINE_TERMINATOR}?
DELIMITED_COMMENT= {NORMAL_DELIMITED_COMMENT} | {EMPTY_DELIMITED_COMMENT}
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
NORMAL_DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
SYM_BACKSLASH=\
SYM_DQUOTE=\"
SYM_SQUOTE=\'
ESCAPE_DQUOTE=\"\"
ESCAPE_SQUOTE=\'\'
ESCAPES={ESCAPE_DQUOTE}|{ESCAPE_SQUOTE}
SYM_SHARP=#
SYM_LINE='__'
SYM_COMMA=,
SYM_SHARPSHARP=##
SYM_LPAREN=\(
SYM_RPAREN=\)
SYM_CASH=\$
SYM_RANGLE=>
SYM_LANGLE=<
DIRECTIVE_NEWLINE={SYM_BACKSLASH}{LINE_TERMINATOR}
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
SYM_SEMICOLON=;
EXIT_CONCAT={ SYM_SHARPSHARP } | { SYM_SEMICOLON } | { SPACE }

%state DIRECTIVE_MODE, MACRO_MODE, SQF_MODE, DIRECTIVE_CONCAT_MODE, STRING_MODE

%%
{ SINGLE_LINE_COMMENT }          { return ParamTypes.SINGLE_LINE_COMMENT; }

{ EMPTY_DELIMITED_COMMENT }      { return ParamTypes.EMPTY_DELIMITED_COMMENT; }

{ DELIMITED_COMMENT }            { return ParamTypes.DELIMITED_COMMENT; }

{ DIRECTIVE_NEWLINE }            {  }

<YYINITIAL> {
  { WHITE_SPACES }               { return TokenType.WHITE_SPACE; }

  "class"                        { return ParamTypes.KW_CLASS; }

  "delete"                       { return ParamTypes.KW_DELETE; }

  "enum"                         { return ParamTypes.KW_ENUM; }

  "@"                            { return ParamTypes.REFERENCE_MODE; }

  "{"                            { return this.currentState.enterLeftCurly(); }

  "}"                            { return this.currentState.enterRightCurly(); }

  "["                            { return ParamTypes.SYM_LSQUARE; }

  "]"                            { return ParamTypes.SYM_RSQUARE; }

  { SYM_SEMICOLON }              { return ParamTypes.SYM_SEMI; }

  ":"                            { return ParamTypes.SYM_COLON; }

  "="                            { return ParamTypes.OP_ASSIGN; }

  "+="                           { return ParamTypes.OP_ADDASSIGN; }

  "-="                           { return ParamTypes.OP_SUBASSIGN; }

  { SYM_DQUOTE }                 { return this.currentState.enterStringMode(ParamStringType.DOUBLE); }

  { SYM_SQUOTE }                 { return this.currentState.enterStringMode(ParamStringType.SINGLE); }

  { ABS_NUMERIC }                { return ParamTypes.ABS_NUMERIC; }

  { SYM_LPAREN }                 { return ParamTypes.SYM_LPARENTHESIS; }

  { SYM_RPAREN }                 { return ParamTypes.SYM_RPARENTHESIS; }

  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }

  { SYM_COMMA }                  { return ParamTypes.SYM_COMMA; }

  { SYM_SHARP }                  { /*return this.enterDirectiveMode();*/}

  { SYM_LINE }                   { /*return this.enterMacroMode();*/ }

  { SYM_SHARPSHARP }             { /*return this.enterConcatMode();*/ }
}

<STRING_MODE> {
  { ESCAPES }                    { return ParamTypes.STRING_ESCAPE; }

  { SYM_CASH }{ABS_IDENTIFIER}   { return LOCALIZED_STRING; }

  { SYM_DQUOTE }                 { return this.currentState.handleStringEnd(ParamStringType.DOUBLE); }

  { SYM_SQUOTE }                 { return this.currentState.handleStringEnd(ParamStringType.SINGLE); }

  { SYM_RANGLE }                 { return this.currentState.handleStringEnd(ParamStringType.INCLUDE); }

  { SYM_SEMICOLON }              { return this.currentState.handleStringEnd(ParamStringType.NONE); }

  { LINE_TERMINATOR }            { return this.currentState.handleStringEnd(ParamStringType.NOT_STRING); }

  [^\"]                          { return ParamTypes.STRING_CONTENT; }
}

<DIRECTIVE_CONCAT_MODE> {
  { SYM_LPAREN }                 { return ParamTypes.SYM_LPARENTHESIS; }

  { SYM_RPAREN }                 { return ParamTypes.SYM_RPARENTHESIS; }

  { EXIT_CONCAT }                { return this.currentState.popStateAndReturn(ParamTypes.EXIT_CONCAT); }
}

<MACRO_MODE> {
  "LINE"                         { return ParamTypes.MACRO_LINE; }

  "FILE"                         { return ParamTypes.MACRO_FILE; }

  "EXEC"                         { return this.currentState.pushStateAndReturn(this.SQF_MODE, ParamTypes.MACRO_EXEC); }

  "EVAL"                         { return this.currentState.pushStateAndReturn(this.SQF_MODE, MACRO_EVAL);}

  "__"                           { return ParamTypes.EXIT_MACRO; }

  [^]                            { return TokenType.BAD_CHARACTER; }
}

<SQF_MODE> {
  { SYM_LPAREN }                 { return ParamTypes.SYM_LPARENTHESIS; }
                                    // pops first to leave sqf-mode then pops again to leave macro mode
  { SYM_RPAREN }                 { return this.currentState.popStateAndReturn(ParamTypes.MACRO, 2); }

  [^]+                           { return ParamTypes.SQF_STATEMENT; }
}

[^] { return TokenType.BAD_CHARACTER; }
