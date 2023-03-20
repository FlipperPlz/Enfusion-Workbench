// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class GeneratedParamVisitor extends PsiElementVisitor {

  public void visitArrayElement(@NotNull GeneratedParamArrayElement o) {
    visitParamElement(o);
  }

  public void visitAssignmentStatement(@NotNull GeneratedParamAssignmentStatement o) {
    visitStatement(o);
    // visitParamStatement(o);
  }

  public void visitClassDeclaration(@NotNull GeneratedParamClassDeclaration o) {
    visitStatement(o);
    // visitParamScope(o);
    // visitParamStatement(o);
  }

  public void visitDeleteStatement(@NotNull GeneratedParamDeleteStatement o) {
    visitStatement(o);
    // visitParamStatement(o);
  }

  public void visitEnumDeclaration(@NotNull GeneratedParamEnumDeclaration o) {
    visitParamElement(o);
  }

  public void visitEnumValue(@NotNull GeneratedParamEnumValue o) {
    visitParamElement(o);
  }

  public void visitIdentifier(@NotNull GeneratedParamIdentifier o) {
    visitParamElement(o);
  }

  public void visitLiteral(@NotNull GeneratedParamLiteral o) {
    visitArrayElement(o);
    // visitParamArrayElement(o);
  }

  public void visitLiteralArray(@NotNull GeneratedParamLiteralArray o) {
    visitArrayElement(o);
    // visitParamArrayElement(o);
  }

  public void visitParamNumeric(@NotNull GeneratedParamParamNumeric o) {
    visitLiteral(o);
    // visitParamLiteral(o);
  }

  public void visitParamString(@NotNull GeneratedParamParamString o) {
    visitLiteral(o);
    // visitParamLiteral(o);
  }

  public void visitStatement(@NotNull GeneratedParamStatement o) {
    visitParamElement(o);
  }

  public void visitParamElement(@NotNull ParamElement o) {
    visitElement(o);
  }

}
