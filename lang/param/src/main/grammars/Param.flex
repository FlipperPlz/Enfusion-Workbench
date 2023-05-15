package com.flipperplz.enfusionWorkbench.languages.param.lexer;

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes;
import com.flipperplz.enfusionWorkbench.languages.param.parser.ParamParserUtil.ParamStringType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.FlexLexer;


%%

%{
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
EMPTY_DELIMITED_COMMENT="/"\*\*?"/"
NORMAL_DELIMITED_COMMENT="/"\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+"/"
ABS_IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
ABS_NUMERIC=(-?[0-9]+(.[0-9]+)?([eE][-+]?[0-9]+)?|0x[a-fA-F0-9]+)
%state STRING_MODE

%%

<YYINITIAL> {
  { LINE_TERMINATOR }            { return ParamTypes.EOL; }

  { WHITE_SPACES }               { return TokenType.WHITE_SPACE; }

  { SINGLE_LINE_COMMENT }        { return ParamTypes.SINGLE_LINE_COMMENT; }

  { EMPTY_DELIMITED_COMMENT }    { return ParamTypes.EMPTY_DELIMITED_COMMENT; }

  { NORMAL_DELIMITED_COMMENT }   { return ParamTypes.DELIMITED_COMMENT; }

  "class"                        { return ParamTypes.KW_CLASS; }

  "delete"                       { return ParamTypes.KW_DELETE; }

  "enum"                         { return ParamTypes.KW_ENUM; }

  { ABS_IDENTIFIER }             { return ParamTypes.ABS_IDENTIFIER; }

  "{"                            { return ParamTypes.SYM_LCURLY; }

  "}"                            { return ParamTypes.SYM_RCURLY; }

  "["                            { return ParamTypes.SYM_LSQUARE; }

  "]"                            { return ParamTypes.SYM_RSQUARE; }

  ";"                            { return ParamTypes.SYM_SEMI; }

  ":"                            { return ParamTypes.SYM_COLON; }

  "="                            { return ParamTypes.OP_ASSIGN; }

  "+="                           { return ParamTypes.OP_ADDASSIGN; }

  "-="                           { return ParamTypes.OP_SUBASSIGN; }

  "\""                           { return ParamTypes.SYM_DQUOTE; }

  ","                            { return ParamTypes.SYM_COMMA; }

  { ABS_NUMERIC }                { return ParamTypes.ABS_NUMERIC; }

  [^]                            { return TokenType.BAD_CHARACTER; }
}