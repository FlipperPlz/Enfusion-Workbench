package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.sdk.language.psi.SimpleTypes;
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

SINGLE_LINE_COMMENT="//".*
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_WHITE_SPACE=[\r\n\t\s]+

ABS_STRING_CONTENT=('""' | ~('\"'))
ABS_DIGITS=[0-9]+;
ABS_NEGATIVE_NUMBER='-'{ABS_DIGITS}
ABS_GENERIC_NUMBER={ABS_NEGATIVE_NUMBER} | {ABS_DIGITS};
ABS_FRACTIONAL_NUMBER={ABS_GENERIC_NUMBER} '.' {ABS_GENERIC_NUMBER};
ABS_HEX_CHAR=[a-fA-F0-9]+;
ABS_SCIENTIFIC_NUMBER={ABS_SIMPLE_NUMBER} [eE] [+-] {ABS_SIMPLE_NUMBER};
ABS_HEX_NUMBER='0x' {ABS_HEX_CHAR}+;
ABS_SIMPLE_NUMBER={ABS_GENERIC_NUMBER} | {ABS_FRACTIONAL_NUMBER};
ABS_ANY_NUMBER={ABS_SCIENTIFIC_NUMBER} | {ABS_SIMPLE_NUMBER} | {ABS_HEX_CHAR};

%%
<YYINITIAL> {
  {ABS_WHITE_SPACE}              { return WHITE_SPACE; }
  {SINGLE_LINE_COMMENT}          { return SINGLE_LINE_COMMENT; }
  {EMPTY_DELIMITED_COMMENT}      { return EMPTY_DELIMITED_COMMENT; }
  {DELIMITED_COMMENT}            { return DELIMITED_COMMENT; }
  {ABS_IDENTIFIER}               { return ABS_IDENTIFIER; }
  \"{ABS_STRING_CONTENT}\"       { return ABS_STRING; }
  {ABS_ANY_NUMBER}               { return ABS_NUMERIC; }

}

[^] { return BAD_CHARACTER; }
