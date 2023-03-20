// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ParamParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return file(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ASSIGNMENT_STATEMENT, CLASS_DECLARATION, DELETE_STATEMENT, STATEMENT),
    create_token_set_(ARRAY_ELEMENT, LITERAL, LITERAL_ARRAY, PARAM_NUMERIC,
      PARAM_STRING),
  };

  /* ********************************************************** */
  // '[' ']' (
  //         OP_ASSIGN  |
  //         OP_ADDASSIGN |
  //         OP_SUBASSIGN
  //     ) literalArray
  static boolean arrayAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayAssignment")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, "[");
    r = r && consumeToken(b, "]");
    r = r && arrayAssignment_2(b, l + 1);
    p = r; // pin = 3
    r = r && literalArray(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // OP_ASSIGN  |
  //         OP_ADDASSIGN |
  //         OP_SUBASSIGN
  private static boolean arrayAssignment_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayAssignment_2")) return false;
    boolean r;
    r = consumeToken(b, OP_ASSIGN);
    if (!r) r = consumeToken(b, OP_ADDASSIGN);
    if (!r) r = consumeToken(b, OP_SUBASSIGN);
    return r;
  }

  /* ********************************************************** */
  // literal | literalArray
  public static boolean arrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayElement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ARRAY_ELEMENT, "<array element>");
    r = literal(b, l + 1);
    if (!r) r = literalArray(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // identifier (arrayAssignment | valueAssignment) ';'
  public static boolean assignmentStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentStatement")) return false;
    if (!nextTokenIs(b, ABS_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_STATEMENT, null);
    r = identifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, assignmentStatement_1(b, l + 1));
    r = p && consumeToken(b, ";") && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arrayAssignment | valueAssignment
  private static boolean assignmentStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentStatement_1")) return false;
    boolean r;
    r = arrayAssignment(b, l + 1);
    if (!r) r = valueAssignment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // 'class' identifier regularClassDecl? ';'
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DECLARATION, "<class declaration>");
    r = consumeToken(b, "class");
    p = r; // pin = 1
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && report_error_(b, classDeclaration_2(b, l + 1)) && r;
    r = p && consumeToken(b, ";") && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // regularClassDecl?
  private static boolean classDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration_2")) return false;
    regularClassDecl(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'delete' identifier ';'
  public static boolean deleteStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deleteStatement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DELETE_STATEMENT, "<delete statement>");
    r = consumeToken(b, "delete");
    p = r; // pin = 1
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && consumeToken(b, ";") && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // 'enum' '{' [enumValue+] '}' ';'
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_DECLARATION, "<enum declaration>");
    r = consumeToken(b, "enum");
    r = r && consumeToken(b, "{");
    p = r; // pin = 2
    r = r && report_error_(b, enumDeclaration_2(b, l + 1));
    r = p && report_error_(b, consumeToken(b, "}")) && r;
    r = p && consumeToken(b, ";") && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [enumValue+]
  private static boolean enumDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_2")) return false;
    enumDeclaration_2_0(b, l + 1);
    return true;
  }

  // enumValue+
  private static boolean enumDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = enumValue(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!enumValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumDeclaration_2_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier ('=' paramNumeric)?
  public static boolean enumValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue")) return false;
    if (!nextTokenIs(b, ABS_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_VALUE, null);
    r = identifier(b, l + 1);
    p = r; // pin = 1
    r = r && enumValue_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('=' paramNumeric)?
  private static boolean enumValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_1")) return false;
    enumValue_1_0(b, l + 1);
    return true;
  }

  // '=' paramNumeric
  private static boolean enumValue_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_ASSIGN);
    r = r && paramNumeric(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // statement* enumDeclaration?
  static boolean file(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = file_0(b, l + 1);
    p = r; // pin = 1
    r = r && file_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // statement*
  private static boolean file_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "file_0", c)) break;
    }
    return true;
  }

  // enumDeclaration?
  private static boolean file_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_1")) return false;
    enumDeclaration(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ABS_IDENTIFIER
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, ABS_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ABS_IDENTIFIER);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // paramString | paramNumeric
  public static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    if (!nextTokenIs(b, "<literal>", ABS_NUMERIC, ABS_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LITERAL, "<literal>");
    r = paramString(b, l + 1);
    if (!r) r = paramNumeric(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '{' arrayElement (',' (arrayElement | &'}'))* '}'
  public static boolean literalArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_ARRAY, "<literal array>");
    r = consumeToken(b, "{");
    p = r; // pin = 1
    r = r && report_error_(b, arrayElement(b, l + 1));
    r = p && report_error_(b, literalArray_2(b, l + 1)) && r;
    r = p && consumeToken(b, "}") && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (arrayElement | &'}'))*
  private static boolean literalArray_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!literalArray_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "literalArray_2", c)) break;
    }
    return true;
  }

  // ',' (arrayElement | &'}')
  private static boolean literalArray_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ",");
    p = r; // pin = 1
    r = r && literalArray_2_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // arrayElement | &'}'
  private static boolean literalArray_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arrayElement(b, l + 1);
    if (!r) r = literalArray_2_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &'}'
  private static boolean literalArray_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, "}");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ABS_NUMERIC
  public static boolean paramNumeric(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramNumeric")) return false;
    if (!nextTokenIs(b, ABS_NUMERIC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ABS_NUMERIC);
    exit_section_(b, m, PARAM_NUMERIC, r);
    return r;
  }

  /* ********************************************************** */
  // ABS_STRING
  public static boolean paramString(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "paramString")) return false;
    if (!nextTokenIs(b, ABS_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ABS_STRING);
    exit_section_(b, m, PARAM_STRING, r);
    return r;
  }

  /* ********************************************************** */
  // (':' identifier)? '{' [statement+] '}'
  static boolean regularClassDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = regularClassDecl_0(b, l + 1);
    r = r && consumeToken(b, "{");
    r = r && regularClassDecl_2(b, l + 1);
    r = r && consumeToken(b, "}");
    exit_section_(b, m, null, r);
    return r;
  }

  // (':' identifier)?
  private static boolean regularClassDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_0")) return false;
    regularClassDecl_0_0(b, l + 1);
    return true;
  }

  // ':' identifier
  private static boolean regularClassDecl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ":");
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [statement+]
  private static boolean regularClassDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_2")) return false;
    regularClassDecl_2_0(b, l + 1);
    return true;
  }

  // statement+
  private static boolean regularClassDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statement(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "regularClassDecl_2_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classDeclaration | deleteStatement | assignmentStatement
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, STATEMENT, "<statement>");
    r = classDeclaration(b, l + 1);
    if (!r) r = deleteStatement(b, l + 1);
    if (!r) r = assignmentStatement(b, l + 1);
    exit_section_(b, l, m, r, false, ParamParser::statementRecover);
    return r;
  }

  /* ********************************************************** */
  // !(';' | identifier)
  static boolean statementRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !statementRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ';' | identifier
  private static boolean statementRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementRecover_0")) return false;
    boolean r;
    r = consumeToken(b, ";");
    if (!r) r = identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // OP_ASSIGN literal
  static boolean valueAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "valueAssignment")) return false;
    if (!nextTokenIs(b, OP_ASSIGN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, OP_ASSIGN);
    p = r; // pin = 1
    r = r && literal(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
