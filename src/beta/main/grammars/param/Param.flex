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
SCHAR=[^\"] | """"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
ABS_WHITESPACE=[\s\t\r\n]

%%
<YYINITIAL> {
  {ABS_WHITESPACE}                  { return WHITE_SPACE; }


  {SINGLE_LINE_COMMENT}          { return ParamTypes.SINGLE_LINE_COMMENT; }
  {EMPTY_DELIMITED_COMMENT}      { return ParamTypes.EMPTY_DELIMITED_COMMENT; }
  {DELIMITED_COMMENT}            { return ParamTypes.DELIMITED_COMMENT; }
  "="                            { return ParamTypes.OP_ASSIGN; }
  "+="                           { return ParamTypes.OP_ADDASSIGN; }
  "-="                           { return ParamTypes.OP_SUBASSIGN; }
  {ABS_IDENTIFIER}               { return ParamTypes.ABS_IDENTIFIER; }
  \"{SCHAR}*\"                   { return ParamTypes.ABS_STRING; }
  {ABS_NUMERIC}                  { return ParamTypes.ABS_NUMERIC; }

}

[^] { return BAD_CHARACTER; }
