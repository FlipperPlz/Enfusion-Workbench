// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.flipperplz.enfusionWorkbench.languages.param.psi.required.ParamElementType;
import com.flipperplz.enfusionWorkbench.languages.param.psi.required.ParamTokenType;
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.*;

public interface ParamTypes {

  IElementType ARRAY_ELEMENT = new ParamElementType("ARRAY_ELEMENT");
  IElementType ASSIGNMENT_STATEMENT = new ParamElementType("ASSIGNMENT_STATEMENT");
  IElementType CLASS_DECLARATION = new ParamElementType("CLASS_DECLARATION");
  IElementType DELETE_STATEMENT = new ParamElementType("DELETE_STATEMENT");
  IElementType ENUM_DECLARATION = new ParamElementType("ENUM_DECLARATION");
  IElementType ENUM_VALUE = new ParamElementType("ENUM_VALUE");
  IElementType IDENTIFIER = new ParamElementType("IDENTIFIER");
  IElementType LITERAL = new ParamElementType("LITERAL");
  IElementType LITERAL_ARRAY = new ParamElementType("LITERAL_ARRAY");
  IElementType PARAM_NUMERIC = new ParamElementType("PARAM_NUMERIC");
  IElementType PARAM_STRING = new ParamElementType("PARAM_STRING");
  IElementType STATEMENT = new ParamElementType("STATEMENT");

  IElementType ABS_IDENTIFIER = new ParamTokenType("ABS_IDENTIFIER");
  IElementType ABS_NUMERIC = new ParamTokenType("ABS_NUMERIC");
  IElementType ABS_STRING = new ParamTokenType("ABS_STRING");
  IElementType DELIMITED_COMMENT = new ParamTokenType("DELIMITED_COMMENT");
  IElementType EMPTY_DELIMITED_COMMENT = new ParamTokenType("EMPTY_DELIMITED_COMMENT");
  IElementType OP_ADDASSIGN = new ParamTokenType("+=");
  IElementType OP_ASSIGN = new ParamTokenType("=");
  IElementType OP_SUBASSIGN = new ParamTokenType("-=");
  IElementType SINGLE_LINE_COMMENT = new ParamTokenType("SINGLE_LINE_COMMENT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ASSIGNMENT_STATEMENT) {
        return new GeneratedParamAssignmentStatementimpl(node);
      }
      else if (type == CLASS_DECLARATION) {
        return new GeneratedParamClassDeclarationimpl(node);
      }
      else if (type == DELETE_STATEMENT) {
        return new GeneratedParamDeleteStatementimpl(node);
      }
      else if (type == ENUM_DECLARATION) {
        return new GeneratedParamEnumDeclarationimpl(node);
      }
      else if (type == ENUM_VALUE) {
        return new GeneratedParamEnumValueimpl(node);
      }
      else if (type == IDENTIFIER) {
        return new GeneratedParamIdentifierimpl(node);
      }
      else if (type == LITERAL_ARRAY) {
        return new GeneratedParamLiteralArrayimpl(node);
      }
      else if (type == PARAM_NUMERIC) {
        return new GeneratedParamParamNumericimpl(node);
      }
      else if (type == PARAM_STRING) {
        return new GeneratedParamParamStringimpl(node);
      }
      else if (type == STATEMENT) {
        return new GeneratedParamStatementimpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
