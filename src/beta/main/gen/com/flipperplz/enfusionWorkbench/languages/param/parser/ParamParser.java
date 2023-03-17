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
    b = adapt_builder_(t, b, this, null);
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

  /* ********************************************************** */
  // SYM_LSBRACKET SYM_RSBRACKET (
  //         OP_ASSIGN  |
  //         OP_ADD_ASSIGN |
  //         OP_SUB_ASSIGN
  //     ) literalArray
  static boolean arrayAssignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayAssignment")) return false;
    if (!nextTokenIs(b, SYM_LSBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeTokens(b, 0, SYM_LSBRACKET, SYM_RSBRACKET);
    r = r && arrayAssignment_2(b, l + 1);
    p = r; // pin = 3
    r = r && literalArray(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // OP_ASSIGN  |
  //         OP_ADD_ASSIGN |
  //         OP_SUB_ASSIGN
  private static boolean arrayAssignment_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayAssignment_2")) return false;
    boolean r;
    r = consumeToken(b, OP_ASSIGN);
    if (!r) r = consumeToken(b, OP_ADD_ASSIGN);
    if (!r) r = consumeToken(b, OP_SUB_ASSIGN);
    return r;
  }

  /* ********************************************************** */
  // literal | literalArray
  static boolean arrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayElement")) return false;
    boolean r;
    r = literal(b, l + 1);
    if (!r) r = literalArray(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // identifier (arrayAssignment | valueAssignment) SYM_SEMICOLON
  public static boolean assignmentStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentStatement")) return false;
    if (!nextTokenIs(b, ABS_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_STATEMENT, null);
    r = identifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, assignmentStatement_1(b, l + 1));
    r = p && consumeToken(b, SYM_SEMICOLON) && r;
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
  // KEYWORD_CLASS identifier regularClassDecl? SYM_SEMICOLON
  public static boolean classDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDeclaration")) return false;
    if (!nextTokenIs(b, KEYWORD_CLASS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DECLARATION, null);
    r = consumeToken(b, KEYWORD_CLASS);
    p = r; // pin = 1
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && report_error_(b, classDeclaration_2(b, l + 1)) && r;
    r = p && consumeToken(b, SYM_SEMICOLON) && r;
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
  // KEYWORD_DELETE identifier SYM_SEMICOLON
  public static boolean deleteStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deleteStatement")) return false;
    if (!nextTokenIs(b, KEYWORD_DELETE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DELETE_STATEMENT, null);
    r = consumeToken(b, KEYWORD_DELETE);
    p = r; // pin = 1
    r = r && report_error_(b, identifier(b, l + 1));
    r = p && consumeToken(b, SYM_SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KEYWORD_ENUM SYM_LBRACKET enumValue* SYM_RBRACKET SYM_SEMICOLON
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    if (!nextTokenIs(b, KEYWORD_ENUM)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ENUM_DECLARATION, null);
    r = consumeTokens(b, 2, KEYWORD_ENUM, SYM_LBRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, enumDeclaration_2(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, SYM_RBRACKET, SYM_SEMICOLON)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // enumValue*
  private static boolean enumDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumDeclaration_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // identifier (OP_ASSIGN ABS_NUMERIC)?
  static boolean enumValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue")) return false;
    if (!nextTokenIs(b, ABS_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = identifier(b, l + 1);
    p = r; // pin = 1
    r = r && enumValue_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (OP_ASSIGN ABS_NUMERIC)?
  private static boolean enumValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_1")) return false;
    enumValue_1_0(b, l + 1);
    return true;
  }

  // OP_ASSIGN ABS_NUMERIC
  private static boolean enumValue_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumValue_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OP_ASSIGN, ABS_NUMERIC);
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
  static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    if (!nextTokenIs(b, "", ABS_NUMERIC, ABS_STRING)) return false;
    boolean r;
    r = paramString(b, l + 1);
    if (!r) r = paramNumeric(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // SYM_LBRACKET arrayElement (',' (arrayElement | &SYM_RBRACKET))* SYM_RBRACKET
  public static boolean literalArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray")) return false;
    if (!nextTokenIs(b, SYM_LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_ARRAY, null);
    r = consumeToken(b, SYM_LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, arrayElement(b, l + 1));
    r = p && report_error_(b, literalArray_2(b, l + 1)) && r;
    r = p && consumeToken(b, SYM_RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' (arrayElement | &SYM_RBRACKET))*
  private static boolean literalArray_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!literalArray_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "literalArray_2", c)) break;
    }
    return true;
  }

  // ',' (arrayElement | &SYM_RBRACKET)
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

  // arrayElement | &SYM_RBRACKET
  private static boolean literalArray_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = arrayElement(b, l + 1);
    if (!r) r = literalArray_2_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &SYM_RBRACKET
  private static boolean literalArray_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalArray_2_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, SYM_RBRACKET);
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
  // (SYM_COLON identifier)? SYM_LBRACKET statement* SYM_RBRACKET
  static boolean regularClassDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl")) return false;
    if (!nextTokenIs(b, "", SYM_COLON, SYM_LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = regularClassDecl_0(b, l + 1);
    r = r && consumeToken(b, SYM_LBRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, regularClassDecl_2(b, l + 1));
    r = p && consumeToken(b, SYM_RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (SYM_COLON identifier)?
  private static boolean regularClassDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_0")) return false;
    regularClassDecl_0_0(b, l + 1);
    return true;
  }

  // SYM_COLON identifier
  private static boolean regularClassDecl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SYM_COLON);
    r = r && identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // statement*
  private static boolean regularClassDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "regularClassDecl_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "regularClassDecl_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // classDeclaration | deleteStatement | assignmentStatement
  static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = classDeclaration(b, l + 1);
    if (!r) r = deleteStatement(b, l + 1);
    if (!r) r = assignmentStatement(b, l + 1);
    exit_section_(b, l, m, r, false, ParamParser::statementRecover);
    return r;
  }

  /* ********************************************************** */
  // !(SYM_SEMICOLON | identifier)
  static boolean statementRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !statementRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SYM_SEMICOLON | identifier
  private static boolean statementRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementRecover_0")) return false;
    boolean r;
    r = consumeToken(b, SYM_SEMICOLON);
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
