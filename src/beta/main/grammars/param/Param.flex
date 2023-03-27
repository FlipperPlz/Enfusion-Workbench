package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
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
  int stringPreviousState = YYINITIAL;
  int directivePreviousState = YYINITIAL;
  int macroPreviousState = YYINITIAL;
  int currentScope = 1;


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

LINE_TERMINATOR=\r?\n
WHITE_SPACES=([\s\t\f])

COMMENT={SINGLE_LINE_COMMENT}|{DELIMITED_COMMENT}
SINGLE_LINE_COMMENT="//".*{LINE_TERMINATOR}?
DELIMITED_COMMENT= {NORMAL_DELIMITED_COMMENT} | {EMPTY_DELIMITED_COMMENT}
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
NORMAL_DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
SYM_BACKSLASH=\
SYM_DQUOTE=\"
STRING_ESCAPE=\"\"
SYM_SHARP=#
SYM_LINE='__'
SYM_COMMA=,
SYM_SHARPSHARP=##
SYM_LPAREN=\(
SYM_RPAREN=\)
DIRECTIVE_NEWLINE={SYM_BACKSLASH}{LINE_TERMINATOR}
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_STRING = \"([^\"]|\"\")*\"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)

%state DIRECTIVE_MODE, MACRO_MODE, SQF_MODE, DIRECTIVE_CONCAT_MODE, STRING_MODE

%%

//Enter Directive Mode
{ SYM_SHARP }                    { this.directivePreviousState = this.yystate(); this.yybegin(this.DIRECTIVE_MODE); return ParamTypes.DIRECTIVE_MODE; } //Directive Mode
//Enter Macro Mode
{ SYM_LINE }                     { this.macroPreviousState = this.yystate(); this.yybegin(this.MACRO_MODE); return ParamTypes.MACRO_MODE; } //Macro Mode
//Enter Concatenation Mode
{ SYM_SHARPSHARP }               { this.yybegin(this.DIRECTIVE_CONCAT_MODE); return ParamTypes.DIRECTIVE_CONCAT; } //Concat Mode


{ ABS_IDENTIFIER }               { return ParamTypes.ABS_IDENTIFIER; }
{ WHITE_SPACES }                 { return ParamTypes.SPACE; }
{ SYM_COMMA }                    { return ParamTypes.SYM_COMMA; }
{ SINGLE_LINE_COMMENT }          { return ParamTypes.SINGLE_LINE_COMMENT; }
{ EMPTY_DELIMITED_COMMENT }      { return ParamTypes.EMPTY_DELIMITED_COMMENT; }
{ DELIMITED_COMMENT }            { return ParamTypes.DELIMITED_COMMENT; }

<YYINITIAL> {
  "class"                        { return ParamTypes.KW_CLASS; }
  "delete"                       { return ParamTypes.KW_DELETE; }
  "enum"                         { return ParamTypes.KW_ENUM; }
  "@"                            { return ParamTypes.REFERENCE_MODE; }
  "{"                            { this.currentScope++; return ParamTypes.SYM_LCURLY; }
  "}"                            { this.currentScope--; return ParamTypes.SYM_RCURLY; }
  "["                            { return ParamTypes.SYM_LSQUARE; }
  "]"                            { return ParamTypes.SYM_RSQUARE; }
  ";"                            { return ParamTypes.SYM_SEMI; }
  ":"                            { return ParamTypes.SYM_COLON; }
  "="                            { return ParamTypes.OP_ASSIGN; }
  "+="                           { return ParamTypes.OP_ADDASSIGN; }
  "-="                           { return ParamTypes.OP_SUBASSIGN; }
  {SYM_DQUOTE}                   { this.stringPreviousState = this.yystate(); this.yybegin(this.STRING_MODE); }
  {ABS_STRING}                   { return ParamTypes.ABS_STRING; }
  {ABS_NUMERIC}                  { return ParamTypes.ABS_NUMERIC; }
}

<STRING_MODE> {
  { STRING_ESCAPE }              { }
  { SYM_DQUOTE }                 { this.yybegin(this.stringPreviousState); return ParamTypes.ABS_STRING; }
  { LINE_TERMINATOR }            { return BAD_CHARACTER; }
  [^]                            { }
}


<DIRECTIVE_MODE> {
  "if"                           { return ParamTypes.KW_IF; }
  "ifdef"                        { return ParamTypes.KW_IFDEF; }
  "ifndef"                       { return ParamTypes.KW_IFNDEF; }
  "include"                      { return ParamTypes.KW_INCLUDE; }
  "define"                        { return ParamTypes.KW_DEFINE; }
  "line"                         { return ParamTypes.KW_LINE; }
  "else"                         { return ParamTypes.KW_ELSE; }
  "endif"                        { this.yybegin(this.directivePreviousState); return ParamTypes.KW_ENDIF; }
  "undef"                        { return ParamTypes.KW_UNDEF; }
  { SYM_LPAREN }                 { return ParamTypes.SYM_LPARENTHESIS; }
  { SYM_RPAREN }                 { return ParamTypes.SYM_RPARENTHESIS; }
  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }
  { DIRECTIVE_NEWLINE }          { return ParamTypes.DIRECTIVE_NEWLINE; }
  { LINE_TERMINATOR }            { this.yybegin(directivePreviousState); return ParamTypes.EXIT_DIRECTIVE; }
  [^]+                           { return ParamTypes.DIRECTIVE_TAIL; }
}

<MACRO_MODE> {
  "LINE"                         { return ParamTypes.MACRO_LINE; }
  "FILE"                         { return ParamTypes.MACRO_FILE; }
  "EXEC"                         { this.yybegin(this.SQF_MODE); return ParamTypes.MACRO_EXEC; }
  "EVAL"                         { this.yybegin(this.SQF_MODE); return ParamTypes.MACRO_EVAL; }
  "__"                           { return ParamTypes.MACRO_MODE; }
  [^]                            { return BAD_CHARACTER; }
}

<SQF_MODE> {
  { SYM_LPAREN }                 { return ParamTypes.SYM_LPARENTHESIS; }
  { SYM_RPAREN }                 { this.yybegin(this.macroPreviousState); return ParamTypes.SYM_RPARENTHESIS; }
  [^]+                           { return ParamTypes.SQF_STATEMENT; }
}

[^] { return BAD_CHARACTER; }
