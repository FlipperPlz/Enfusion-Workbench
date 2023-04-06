package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamLexerState;
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType;
import com.flipperplz.enfusionWorkbench.psi.lexer.EnfusionFlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;

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
WHITE_SPACES=[\s\t]
SINGLE_LINE_COMMENT="//".*{LINE_TERMINATOR}?
DELIMITED_COMMENT= {NORMAL_DELIMITED_COMMENT} | {EMPTY_DELIMITED_COMMENT}
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
NORMAL_DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
SYM_BACKSLASH=\
ESCAPE_DQUOTE=\"\"
ESCAPE_SQUOTE=\'\'
ESCAPES={ESCAPE_DQUOTE}|{ESCAPE_SQUOTE}
SYM_SHARP=#
SYM_SHARPSHARP=##
SYM_CASH=\$
LOCALIZED_STRING={SYM_CASH}{ABS_IDENTIFIER}
DIRECTIVE_NEWLINE={SYM_BACKSLASH}{LINE_TERMINATOR}
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_STRING=\"((\"\"|[^\"])+)\"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
SIMPLE_NUMERIC=(0|[1-9]\d*)(\.\d+)?
%state STRING_MODE, PROCEDURAL_TEXTURE_MODE

%%
{ SINGLE_LINE_COMMENT }          { return ParamTypes.SINGLE_LINE_COMMENT; }

{ EMPTY_DELIMITED_COMMENT }      { return ParamTypes.EMPTY_DELIMITED_COMMENT; }

{ DELIMITED_COMMENT }            { return ParamTypes.DELIMITED_COMMENT; }

{ DIRECTIVE_NEWLINE }            {  }

<YYINITIAL> {
  { LINE_TERMINATOR }            { return TokenType.WHITE_SPACE; }

  { WHITE_SPACES }               { return TokenType.WHITE_SPACE; }

  "class"                        { return ParamTypes.KW_CLASS; }

  "delete"                       { return ParamTypes.KW_DELETE; }

  "enum"                         { return ParamTypes.KW_ENUM; }

  "@"                            { return ParamTypes.REFERENCE_MODE; }

  "<"                            { this.currentState.enterStringMode(ParamStringType.INCLUDE); }

  "__LINE__"                     { return ParamTypes.MACRO_LINE; }

  "__FILE__"                     { return ParamTypes.MACRO_FILE; }

  "{"                            { return this.currentState.enterLeftCurly(); }

  "}"                            { return this.currentState.enterRightCurly(); }

  "["                            { return ParamTypes.SYM_LSQUARE; }

  "]"                            { return ParamTypes.SYM_RSQUARE; }

  ";"                            {
          this.currentState.setAssumeInProperty(false);
          return ParamTypes.SYM_SEMI;
      }

  ":"                            { return ParamTypes.SYM_COLON; }

  "="                            {
          this.currentState.setAssumeInProperty(true);
          return ParamTypes.OP_ASSIGN;
      }

  "+="                           {
          this.currentState.setAssumeInProperty(true);
          return ParamTypes.OP_ADDASSIGN;
      }

  "-="                           {
          this.currentState.setAssumeInProperty(true);
          return ParamTypes.OP_SUBASSIGN;
      }

  "\""                           { return this.currentState.enterStringMode(ParamStringType.DOUBLE); }

  "'"                            { return this.currentState.enterStringMode(ParamStringType.SINGLE); }

  "("                            { return ParamTypes.SYM_LPARENTHESIS; }

  ")"                            { return ParamTypes.SYM_RPARENTHESIS; }

  ","                            { return ParamTypes.SYM_COMMA; }

  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }

  { ABS_NUMERIC }                { return ParamTypes.ABS_NUMERIC; }

  { SYM_SHARP }                  { /*return this.enterDirectiveMode();*/}

  { SYM_SHARPSHARP }             { /*return this.enterConcatMode();*/ }

  [^]                            { return this.currentState.enterStringMode(ParamStringType.SINGLE); }
}

<STRING_MODE> {
  { ESCAPES }                    { return ParamTypes.STRING_ESCAPE; }

  { LOCALIZED_STRING }           { return ParamTypes.LOCALIZED_STRING; }

  "#("                           {
          this.currentState.setCurrentParenthesisLevel(2);
          this.yypushback(1);
          return this.currentState.pushStateAndReturn(this.PROCEDURAL_TEXTURE_MODE, ParamTypes.PROCEDURAL_TEXTURE_START);
      }

  "\""                           { return this.currentState.handleStringEnd(ParamStringType.DOUBLE); }

  "'"                            { return this.currentState.handleStringEnd(ParamStringType.SINGLE); }

  ">"                            { return this.currentState.handleStringEnd(ParamStringType.INCLUDE); }

  ";"                            { return this.currentState.handleStringEnd(ParamStringType.AMBIGUOUS); }

  ","                            { return this.currentState.handleStringEnd(ParamStringType.AMBIGUOUS); }

  "}"                            { return this.currentState.handleStringEnd(ParamStringType.AMBIGUOUS); }

  { LINE_TERMINATOR }            { return this.currentState.handleStringEnd(ParamStringType.AMBIGUOUS); }

  [^\"]                          { return ParamTypes.STRING_CONTENTS; }
}

<PROCEDURAL_TEXTURE_MODE> {
    ")"                          {
          this.currentState.popParenthesisLevel();
          if(this.currentState.getCurrentParenthesisLevel() == 0) this.currentState.popState();

          return ParamTypes.SYM_RPARENTHESIS;
          }
    "("                          { return ParamTypes.SYM_LPARENTHESIS; }

    "rgb"|"argb"|"ai"|"a"|"i"    { return ParamTypes.PROCEDURAL_TEXTURE_FILTER; }

    "color"|"r2t"|"perlinNoise"|"irradiance"|"Fresnel"|"fresnelGlass"|"text"|
    "waterIrradiance"|"treeCrown"|"treeCrownAmb"|"point"|"dither"|"ui"   {
          return ParamTypes.PROCEDURAL_FUNCTION_TYPE;
    }

    ","                          { return ParamTypes.SYM_COMMA; }

    { WHITE_SPACES }             { return TokenType.BAD_CHARACTER; }

    { SIMPLE_NUMERIC }           { return ParamTypes.ABS_NUMERIC; }

    { ABS_STRING }               { return ParamTypes.STRING_LITERAL; }

    { ABS_IDENTIFIER }           { return ParamTypes.ABS_IDENTIFIER; }

    [^]                          { return this.currentState.popStateAndReturn(ParamTypes.STRING_CONTENT); }
}
