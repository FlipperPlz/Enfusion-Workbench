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

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return file(builder_, level_ + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ARRAY_ELEMENT, LITERAL, LITERAL_ARRAY, PARAM_NUMERIC,
      PARAM_STRING),
  };

  /* ********************************************************** */
  // '[' ']' (
  //         OP_ASSIGN  |
  //         OP_ADDASSIGN |
  //         OP_SUBASSIGN
  //     ) literalArray
  static boolean arrayAssignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayAssignment")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, "[");
    result_ = result_ && consumeToken(builder_, "]");
    result_ = result_ && arrayAssignment_2(builder_, level_ + 1);
    pinned_ = result_; // pin = 3
    result_ = result_ && literalArray(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // OP_ASSIGN  |
  //         OP_ADDASSIGN |
  //         OP_SUBASSIGN
  private static boolean arrayAssignment_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayAssignment_2")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_ASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_ADDASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_SUBASSIGN);
    return result_;
  }

  /* ********************************************************** */
  // literal | literalArray
  public static boolean arrayElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayElement")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ARRAY_ELEMENT, "<array element>");
    result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = literalArray(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // identifier (arrayAssignment | valueAssignment)
  public static boolean assignmentStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignmentStatement")) return false;
    if (!nextTokenIs(builder_, ABS_IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGNMENT_STATEMENT, null);
    result_ = identifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && assignmentStatement_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arrayAssignment | valueAssignment
  private static boolean assignmentStatement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignmentStatement_1")) return false;
    boolean result_;
    result_ = arrayAssignment(builder_, level_ + 1);
    if (!result_) result_ = valueAssignment(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // 'class' identifier regularClassDecl?
  public static boolean classDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "classDeclaration")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CLASS_DECLARATION, "<class declaration>");
    result_ = consumeToken(builder_, "class");
    result_ = result_ && identifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && classDeclaration_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // regularClassDecl?
  private static boolean classDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "classDeclaration_2")) return false;
    regularClassDecl(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // 'delete' identifier
  public static boolean deleteStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "deleteStatement")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DELETE_STATEMENT, "<delete statement>");
    result_ = consumeToken(builder_, "delete");
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // 'enum' '{' [enumValue+] '}' ';'
  public static boolean enumDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDeclaration")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_DECLARATION, "<enum declaration>");
    result_ = consumeToken(builder_, "enum");
    result_ = result_ && consumeToken(builder_, "{");
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, enumDeclaration_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, "}")) && result_;
    result_ = pinned_ && consumeToken(builder_, ";") && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // [enumValue+]
  private static boolean enumDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDeclaration_2")) return false;
    enumDeclaration_2_0(builder_, level_ + 1);
    return true;
  }

  // enumValue+
  private static boolean enumDeclaration_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDeclaration_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = enumValue(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!enumValue(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enumDeclaration_2_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // identifier ('=' paramNumeric)?
  public static boolean enumValue(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumValue")) return false;
    if (!nextTokenIs(builder_, ABS_IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_VALUE, null);
    result_ = identifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && enumValue_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ('=' paramNumeric)?
  private static boolean enumValue_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumValue_1")) return false;
    enumValue_1_0(builder_, level_ + 1);
    return true;
  }

  // '=' paramNumeric
  private static boolean enumValue_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumValue_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && paramNumeric(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // statement* enumDeclaration?
  static boolean file(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = file_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && file_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // statement*
  private static boolean file_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "file_0", pos_)) break;
    }
    return true;
  }

  // enumDeclaration?
  private static boolean file_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file_1")) return false;
    enumDeclaration(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ABS_IDENTIFIER
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    if (!nextTokenIs(builder_, ABS_IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ABS_IDENTIFIER);
    exit_section_(builder_, marker_, IDENTIFIER, result_);
    return result_;
  }

  /* ********************************************************** */
  // paramString | paramNumeric
  public static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    if (!nextTokenIs(builder_, "<literal>", ABS_NUMERIC, ABS_STRING)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, LITERAL, "<literal>");
    result_ = paramString(builder_, level_ + 1);
    if (!result_) result_ = paramNumeric(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' arrayElement (',' (arrayElement | &'}'))* '}'
  public static boolean literalArray(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL_ARRAY, "<literal array>");
    result_ = consumeToken(builder_, "{");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, arrayElement(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, literalArray_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, "}") && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (',' (arrayElement | &'}'))*
  private static boolean literalArray_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!literalArray_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "literalArray_2", pos_)) break;
    }
    return true;
  }

  // ',' (arrayElement | &'}')
  private static boolean literalArray_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_2_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, ",");
    pinned_ = result_; // pin = 1
    result_ = result_ && literalArray_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arrayElement | &'}'
  private static boolean literalArray_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_2_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arrayElement(builder_, level_ + 1);
    if (!result_) result_ = literalArray_2_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean literalArray_2_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_2_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, "}");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ABS_NUMERIC
  public static boolean paramNumeric(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramNumeric")) return false;
    if (!nextTokenIs(builder_, ABS_NUMERIC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ABS_NUMERIC);
    exit_section_(builder_, marker_, PARAM_NUMERIC, result_);
    return result_;
  }

  /* ********************************************************** */
  // ABS_STRING
  public static boolean paramString(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramString")) return false;
    if (!nextTokenIs(builder_, ABS_STRING)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ABS_STRING);
    exit_section_(builder_, marker_, PARAM_STRING, result_);
    return result_;
  }

  /* ********************************************************** */
  // (':' identifier)? '{' statement* '}'
  static boolean regularClassDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = regularClassDecl_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, "{");
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, regularClassDecl_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, "}") && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (':' identifier)?
  private static boolean regularClassDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl_0")) return false;
    regularClassDecl_0_0(builder_, level_ + 1);
    return true;
  }

  // ':' identifier
  private static boolean regularClassDecl_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ":");
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // statement*
  private static boolean regularClassDecl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "regularClassDecl_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (classDeclaration | deleteStatement | assignmentStatement) ';'
  public static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STATEMENT, "<statement>");
    result_ = statement_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, ";");
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // classDeclaration | deleteStatement | assignmentStatement
  private static boolean statement_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_0")) return false;
    boolean result_;
    result_ = classDeclaration(builder_, level_ + 1);
    if (!result_) result_ = deleteStatement(builder_, level_ + 1);
    if (!result_) result_ = assignmentStatement(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // OP_ASSIGN literal
  static boolean valueAssignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "valueAssignment")) return false;
    if (!nextTokenIs(builder_, OP_ASSIGN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, OP_ASSIGN);
    pinned_ = result_; // pin = 1
    result_ = result_ && literal(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

}
