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

EOL=\R
WHITE_SPACE=\s+

SINGLE_LINE_COMMENT="//".*\r\n
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_STRING=\"(\"\" | .)\"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
ABS_WHITESPACE=[\s\t\r\n]

%%
<YYINITIAL> {
  {WHITE_SPACE}                  { return WHITE_SPACE; }


  {SINGLE_LINE_COMMENT}          { return SINGLE_LINE_COMMENT; }
  {EMPTY_DELIMITED_COMMENT}      { return EMPTY_DELIMITED_COMMENT; }
  {DELIMITED_COMMENT}            { return DELIMITED_COMMENT; }
  {ABS_IDENTIFIER}               { return ABS_IDENTIFIER; }
  {ABS_STRING}                   { return ABS_STRING; }
  {ABS_NUMERIC}                  { return ABS_NUMERIC; }
  {ABS_WHITESPACE}               { return ABS_WHITESPACE; }

}

[^] { return BAD_CHARACTER; }
