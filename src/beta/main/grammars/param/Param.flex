package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.FlexLexer;


%%

%{
    private ParamStringType xxStringType = ParamStringType.NOT_STRING;
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
ABS_STRING=\"((\"\"|[^\"])+)\"
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
SIMPLE_NUMERIC=(0|[1-9]\d*)(\.\d+)?
%state STRING_MODE, PROCEDURAL_TEXTURE_MODE, DEFINE_MODE

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

  "__LINE__"                     { return ParamTypes.MACRO_LINE; }

  "__FILE__"                     { return ParamTypes.MACRO_FILE; }

  "include"                      { return ParamTypes.KW_INCLUDE; }

  "define"                       {
          yybegin(DEFINE_MODE);
          return ParamTypes.KW_DEFINE;
      }

  "undef"                        { return ParamTypes.KW_UNDEFINE; }

  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }

  "@"                            { return ParamTypes.REFERENCE_MODE; }

  "<"                            {
          xxStringType = ParamStringType.INCLUDE;
          yybegin(STRING_MODE);
          return ParamTypes.STRING_INCLUDE_START;
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

  "\""                            {
                xxStringType = ParamStringType.DOUBLE;
                yybegin(STRING_MODE);
                return ParamTypes.STRING_DOUBLE_START;
             }

  "'"                            {
               xxStringType = ParamStringType.SINGLE;
               yybegin(STRING_MODE);
               return ParamTypes.STRING_SINGLE_START;
            }

  "("                            { return ParamTypes.SYM_LPARENTHESIS; }

  ")"                            { return ParamTypes.SYM_RPARENTHESIS; }

  ","                            { return ParamTypes.SYM_COMMA; }

  { ABS_NUMERIC }                { return ParamTypes.ABS_NUMERIC; }

  { SYM_SHARP }                  { return ParamTypes.DIRECTIVE_START; }

  { SYM_SHARPSHARP }             { /*return this.enterConcatMode();*/ }

  [^]                            {
          xxStringType = ParamStringType.AMBIGUOUS;
          yybegin(this.STRING_MODE);
          return ParamTypes.STRING_AMBIGUOUS_START;
      }
}

<STRING_MODE> {
  { ESCAPES }                    { return ParamTypes.STRING_ESCAPE; }

  { LOCALIZED_STRING }           { return ParamTypes.LOCALIZED_STRING; }

  "#("                           {
          xxCurrentCounter = 2;
          yybegin(PROCEDURAL_TEXTURE_MODE);
          yypushback(1);
          return ParamTypes.PROCEDURAL_TEXTURE_START;
      }

  "\""                           {
          if (xxStringType == ParamStringType.DOUBLE) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_DOUBLE_END;
          } else return ParamTypes.STRING_CONTENTS;
      }

  "'"                            {
          if (xxStringType == ParamStringType.SINGLE) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_SINGLE_END;
          } else return ParamTypes.STRING_CONTENTS;
      }

  ">"                            {
          if (xxStringType == ParamStringType.SINGLE) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_INCLUDE_END;
          } else return ParamTypes.STRING_CONTENTS;
       }

  ";"                            {
          if (xxStringType == ParamStringType.AMBIGUOUS) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_AMBIGUOUS_END;
          } else return ParamTypes.STRING_CONTENTS;
       }

  ","                            {
          if (xxStringType == ParamStringType.AMBIGUOUS) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_AMBIGUOUS_END;
          } else return ParamTypes.STRING_CONTENTS;
       }

  "}"                            {
          if (xxStringType == ParamStringType.AMBIGUOUS) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_AMBIGUOUS_END;
          } else return ParamTypes.STRING_CONTENTS;
      }

  { LINE_TERMINATOR }            {
          if (xxStringType == ParamStringType.AMBIGUOUS) {
              xxStringType = ParamStringType.NOT_STRING;
              yybegin(YYINITIAL);
              return ParamTypes.STRING_AMBIGUOUS_END;
          } else return ParamTypes.STRING_CONTENTS;
      }

  [^\"]                          { return ParamTypes.STRING_CONTENTS; }
}

<PROCEDURAL_TEXTURE_MODE> {
    ")"                          {
          xxCurrentCounter--;
          if(xxCurrentCounter == 0) yybegin(STRING_MODE);

          return ParamTypes.SYM_RPARENTHESIS;
       }
    "("                          { return ParamTypes.SYM_LPARENTHESIS; }

    ","                          { return ParamTypes.SYM_COMMA; }

    { WHITE_SPACES }             { return TokenType.BAD_CHARACTER; }

    { SIMPLE_NUMERIC }           { return ParamTypes.ABS_NUMERIC; }

    { ABS_STRING }               { return ParamTypes.STRING_LITERAL; }

    { ABS_IDENTIFIER }           { return ParamTypes.ABS_IDENTIFIER; }

    [^]                          { yybegin(STRING_MODE); return ParamTypes.STRING_CONTENTS; }
}
