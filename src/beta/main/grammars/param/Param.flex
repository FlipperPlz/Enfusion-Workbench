package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;

%%

%{
  public ParamLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class ParamLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE=[\s\t]+

SINGLE_LINE_COMMENT="//".*[\r\n]
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
ABS_STRING=\"((\"\"|[^\"])+)\"

%%
<YYINITIAL> {

  {WHITE_SPACE}                  { return WHITE_SPACE; }

  "class"                        { return KEYWORD_CLASS; }
  "enum"                         { return KEYWORD_ENUM; }
  "delete"                       { return KEYWORD_DELETE; }
  "{"                            { return SYM_LBRACKET; }
  "}"                            { return SYM_RBRACKET; }
  ";"                            { return SYM_SEMICOLON; }
  ":"                            { return SYM_COLON; }
  "["                            { return SYM_LSBRACKET; }
  "]"                            { return SYM_RSBRACKET; }
  "="                            { return OP_ASSIGN; }
  "+="                           { return OP_ADD_ASSIGN; }
  "-="                           { return OP_SUB_ASSIGN; }

  {SINGLE_LINE_COMMENT}          { return SINGLE_LINE_COMMENT; }
  {EMPTY_DELIMITED_COMMENT}      { return EMPTY_DELIMITED_COMMENT; }
  {DELIMITED_COMMENT}            { return DELIMITED_COMMENT; }
  {ABS_IDENTIFIER}               { return ABS_IDENTIFIER; }
  {ABS_STRING}                   { return ABS_STRING; }
  {ABS_NUMERIC}                  { return ABS_NUMERIC; }
}

[^] { return BAD_CHARACTER; }
