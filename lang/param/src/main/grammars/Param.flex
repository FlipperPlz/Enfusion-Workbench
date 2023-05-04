package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
import com.flipperplz.enfusionWorkbench.languages.param.parser.ParamParserUtil.ParamStringType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.FlexLexer;


%%

%{
    private ParamStringType xxStringType = null;
    private int xxCurrentCounter = 0;

    public ParamLexer() { this((java.io.Reader)null); }
%}

%public
%class ParamLexer
%implements FlexLexer
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
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
%state STRING_MODE

%%
{ DIRECTIVE_NEWLINE }            {  }

<YYINITIAL> {
  { LINE_TERMINATOR }            { return ParamTypes.EOL; }

  { WHITE_SPACES }               { return TokenType.WHITE_SPACE; }

  { SINGLE_LINE_COMMENT }        { return ParamTypes.SINGLE_LINE_COMMENT; }

  { EMPTY_DELIMITED_COMMENT }    { return ParamTypes.EMPTY_DELIMITED_COMMENT; }

  { DELIMITED_COMMENT }          { return ParamTypes.DELIMITED_COMMENT; }

  "class"                        { return ParamTypes.KW_CLASS; }

  "delete"                       { return ParamTypes.KW_DELETE; }

  "enum"                         { return ParamTypes.KW_ENUM; }

  "include"                      { return ParamTypes.KW_INCLUDE; }

  "define"                       { return ParamTypes.KW_DEFINE; }

  "undef"                        { return ParamTypes.KW_UNDEFINE; }

  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }

  "@"                            { return ParamTypes.SYM_ASPERAND; }

  "<"                            {
          xxStringType = ParamStringType.INCLUDE;
          yybegin(STRING_MODE);
          return ParamTypes.SYM_LANGLE;
       }

  "{"                            { return ParamTypes.SYM_LCURLY; }

  "}"                            { return ParamTypes.SYM_RCURLY; }

  "["                            { return ParamTypes.SYM_LSQUARE; }

  "]"                            { return ParamTypes.SYM_RSQUARE; }

  ";"                            { return ParamTypes.SYM_SEMI; }

  ":"                            { return ParamTypes.SYM_COLON; }

  "="                            { return ParamTypes.OP_ASSIGN; }

  "+="                           { return ParamTypes.OP_ADDASSIGN; }

  "-="                           { return ParamTypes.OP_SUBASSIGN; }

  "\""                           {
               xxStringType = ParamStringType.DOUBLE;
               yybegin(STRING_MODE);
               return ParamTypes.SYM_DQUOTE;
            }

  "'"                            {
               xxStringType = ParamStringType.SINGLE;
               yybegin(STRING_MODE);
               return ParamTypes.SYM_SQUOTE;
            }

  "("                            { return ParamTypes.SYM_LPARENTHESIS; }

  ")"                            { return ParamTypes.SYM_RPARENTHESIS; }

  ","                            { return ParamTypes.SYM_COMMA; }

  { ABS_NUMERIC }                { return ParamTypes.ABS_NUMERIC; }

  { SYM_SHARP }                  { return ParamTypes.SYM_POUND; }

//  { SYM_SHARPSHARP }             { /*return this.enterConcatMode();*/ }

  [^]                            {
          xxStringType = ParamStringType.UNQUOTED;
          yybegin(this.STRING_MODE);
          return ParamTypes.STRING_CONTENTS;
      }
}

<STRING_MODE> {
  { ESCAPES }                    { return ParamTypes.STRING_ESCAPE; }

  { LOCALIZED_STRING }           { return ParamTypes.LOCALIZED_STRING; }

  "\""                           {
          if (xxStringType == ParamStringType.DOUBLE) {
              xxStringType = null;
              yybegin(YYINITIAL);
              return ParamTypes.SYM_DQUOTE;
          } else return ParamTypes.STRING_CONTENTS;
      }

  "'"                            {
          if (xxStringType == ParamStringType.SINGLE) {
              xxStringType = null;
              yybegin(YYINITIAL);
              return ParamTypes.SYM_SQUOTE;
          } else return ParamTypes.STRING_CONTENTS;
      }

  ">"                            {
          if (xxStringType == ParamStringType.INCLUDE) {
              xxStringType = null;
              yybegin(YYINITIAL);
              return ParamTypes.SYM_RANGLE;
          } else return ParamTypes.STRING_CONTENTS;
       }

  ";"                            {
          if (xxStringType == ParamStringType.UNQUOTED) {
              xxStringType = null;
              yybegin(YYINITIAL);
              yypushback(1);
          } else return ParamTypes.STRING_CONTENTS;
       }

  ","                            {
          if (xxStringType == ParamStringType.UNQUOTED) {
              xxStringType = null;
              yybegin(YYINITIAL);
              yypushback(1);
          } else return ParamTypes.STRING_CONTENTS;
       }

  "}"                            {
          if (xxStringType == ParamStringType.UNQUOTED) {
              xxStringType = null;
              yybegin(YYINITIAL);
              yypushback(1);
          } else return ParamTypes.STRING_CONTENTS;
      }

  { LINE_TERMINATOR }            {
          if (xxStringType == ParamStringType.UNQUOTED) {
              xxStringType = null;
              yybegin(YYINITIAL);
              yypushback(1);
          } else return ParamTypes.STRING_CONTENTS;
      }

  [^\"]                          { return ParamTypes.STRING_CONTENTS; }
}
