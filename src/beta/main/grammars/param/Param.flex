package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;import com.intellij.lexer.FlexLexer;
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

EOL=\R

SINGLE_LINE_COMMENT="//".*\r\n
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_STRING = \"([^\"]|\"\")*\"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
ABS_WHITESPACE=[\s\t\r\n]
ABS_WHITESPACE=[\s\t\r\n]
ABS_INCLUDE_STRING=\<([^\"]|\"\")*\>
ABS_DEFINE_VALUE=([^\n]|\\\n)*

%%
<YYINITIAL> {

  {SINGLE_LINE_COMMENT}          { return ParamTypes.SINGLE_LINE_COMMENT; }
  {EMPTY_DELIMITED_COMMENT}      { return ParamTypes.EMPTY_DELIMITED_COMMENT; }
  {DELIMITED_COMMENT}            { return ParamTypes.DELIMITED_COMMENT; }
  "class"                        { return ParamTypes.KW_CLASS; }
  "delete"                       { return ParamTypes.KW_DELETE; }
  "enum"                         { return ParamTypes.KW_ENUM; }
  "EVAL"                         { return ParamTypes.MACRO_EVAL; }
  "EXEC"                         { return ParamTypes.MACRO_EXEC; }
  "LINE"                         { return ParamTypes.MACRO_LINE; }
  "("                            { return ParamTypes.SYM_LPARENTHESIS; }
  ")"                            { return ParamTypes.SYM_RPARENTHESIS; }
  "@"                            { return ParamTypes.REFERENCE_MODE; }
  "("                            { return ParamTypes.SYM_LPARENTHESIS; }


  "{"                            { return ParamTypes.SYM_LCURLY; }
  "}"                            { return ParamTypes.SYM_RCURLY; }
  "["                            { return ParamTypes.SYM_LSQUARE; }
  "]"                            { return ParamTypes.SYM_RSQUARE; }
  ";"                            { return ParamTypes.SYM_SEMI; }
  ":"                            { return ParamTypes.SYM_COLON; }
  ","                            { return ParamTypes.SYM_COMMA; }
  "="                            { return ParamTypes.OP_ASSIGN; }
  "+="                           { return ParamTypes.OP_ADDASSIGN; }
  "-="                           { return ParamTypes.OP_SUBASSIGN; }
  {ABS_DEFINE_VALUE}             { return ParamTypes.ABS_DEFINE_VALUE; }
  {ABS_IDENTIFIER}               { return ParamTypes.ABS_IDENTIFIER; }
  {ABS_STRING}                   { return ParamTypes.ABS_STRING; }
  {ABS_NUMERIC}                  { return ParamTypes.ABS_NUMERIC; }
  {ABS_INCLUDE_STRING}           { return ParamTypes.ABS_INCLUDE_STRING; }

}

[^] { return BAD_CHARACTER; }
