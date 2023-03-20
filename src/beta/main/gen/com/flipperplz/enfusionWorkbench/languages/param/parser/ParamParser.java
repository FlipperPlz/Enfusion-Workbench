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
  // SYM_LSQUARE SYM_RSQUARE (
  //         OP_ASSIGN  |
  //         OP_ADDASSIGN |
  //         OP_SUBASSIGN
  //     ) literalArray
  static boolean arrayAssignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayAssignment")) return false;
    if (!nextTokenIs(builder_, SYM_LSQUARE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeTokens(builder_, 0, SYM_LSQUARE, SYM_RSQUARE);
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
  // KW_CLASS identifier regularClassDecl?
  public static boolean classDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "classDeclaration")) return false;
    if (!nextTokenIs(builder_, KW_CLASS)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CLASS_DECLARATION, null);
    result_ = consumeToken(builder_, KW_CLASS);
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
  // KW_DELETE identifier
  public static boolean deleteStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "deleteStatement")) return false;
    if (!nextTokenIs(builder_, KW_DELETE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KW_DELETE);
    result_ = result_ && identifier(builder_, level_ + 1);
    exit_section_(builder_, marker_, DELETE_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // KW_DELETE SYM_LCURLY [enumValue+] SYM_RCURLY SYM_SEMI
  public static boolean enumDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumDeclaration")) return false;
    if (!nextTokenIs(builder_, KW_DELETE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_DECLARATION, null);
    result_ = consumeTokens(builder_, 2, KW_DELETE, SYM_LCURLY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, enumDeclaration_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeTokens(builder_, -1, SYM_RCURLY, SYM_SEMI)) && result_;
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
  // identifier (OP_ASSIGN paramNumeric)?
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

  // (OP_ASSIGN paramNumeric)?
  private static boolean enumValue_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enumValue_1")) return false;
    enumValue_1_0(builder_, level_ + 1);
    return true;
  }

  // OP_ASSIGN paramNumeric
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
  // SYM_LCURLY (arrayElement (SYM_COMMA (arrayElement | &SYM_RCURLY))*)? SYM_RCURLY
  public static boolean literalArray(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray")) return false;
    if (!nextTokenIs(builder_, SYM_LCURLY)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL_ARRAY, null);
    result_ = consumeToken(builder_, SYM_LCURLY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, literalArray_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, SYM_RCURLY) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (arrayElement (SYM_COMMA (arrayElement | &SYM_RCURLY))*)?
  private static boolean literalArray_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1")) return false;
    literalArray_1_0(builder_, level_ + 1);
    return true;
  }

  // arrayElement (SYM_COMMA (arrayElement | &SYM_RCURLY))*
  private static boolean literalArray_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = arrayElement(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && literalArray_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (SYM_COMMA (arrayElement | &SYM_RCURLY))*
  private static boolean literalArray_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!literalArray_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "literalArray_1_0_1", pos_)) break;
    }
    return true;
  }

  // SYM_COMMA (arrayElement | &SYM_RCURLY)
  private static boolean literalArray_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1_0_1_0")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, SYM_COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && literalArray_1_0_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // arrayElement | &SYM_RCURLY
  private static boolean literalArray_1_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1_0_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arrayElement(builder_, level_ + 1);
    if (!result_) result_ = literalArray_1_0_1_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &SYM_RCURLY
  private static boolean literalArray_1_0_1_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literalArray_1_0_1_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, SYM_RCURLY);
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
  // (SYM_COLON identifier)? SYM_LCURLY statement* SYM_RCURLY
  static boolean regularClassDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl")) return false;
    if (!nextTokenIs(builder_, "", SYM_COLON, SYM_LCURLY)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = regularClassDecl_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SYM_LCURLY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, regularClassDecl_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, SYM_RCURLY) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (SYM_COLON identifier)?
  private static boolean regularClassDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl_0")) return false;
    regularClassDecl_0_0(builder_, level_ + 1);
    return true;
  }

  // SYM_COLON identifier
  private static boolean regularClassDecl_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "regularClassDecl_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SYM_COLON);
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
  // (classDeclaration | deleteStatement | assignmentStatement) SYM_SEMI
  public static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STATEMENT, "<statement>");
    result_ = statement_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, SYM_SEMI);
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
