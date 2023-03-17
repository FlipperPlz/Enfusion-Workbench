// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class GeneratedParamVisitor extends PsiElementVisitor {

  public void visitAssignmentStatement(@NotNull GeneratedParamAssignmentStatement o) {
    visitParamStatement(o);
  }

  public void visitClassDeclaration(@NotNull GeneratedParamClassDeclaration o) {
    visitParamScope(o);
    // visitParamStatement(o);
  }

  public void visitDeleteStatement(@NotNull GeneratedParamDeleteStatement o) {
    visitParamStatement(o);
  }

  public void visitEnumDeclaration(@NotNull GeneratedParamEnumDeclaration o) {
    visitParamElement(o);
  }

  public void visitIdentifier(@NotNull GeneratedParamIdentifier o) {
    visitParamElement(o);
  }

  public void visitLiteralArray(@NotNull GeneratedParamLiteralArray o) {
    visitParamArrayElement(o);
  }

  public void visitParamNumeric(@NotNull GeneratedParamParamNumeric o) {
    visitParamLiteral(o);
  }

  public void visitParamString(@NotNull GeneratedParamParamString o) {
    visitParamLiteral(o);
  }

  public void visitParamArrayElement(@NotNull ParamArrayElement o) {
    visitElement(o);
  }

  public void visitParamLiteral(@NotNull ParamLiteral o) {
    visitElement(o);
  }

  public void visitParamScope(@NotNull ParamScope o) {
    visitElement(o);
  }

  public void visitParamStatement(@NotNull ParamStatement o) {
    visitElement(o);
  }

  public void visitParamElement(@NotNull ParamElement o) {
    visitElement(o);
  }

}
