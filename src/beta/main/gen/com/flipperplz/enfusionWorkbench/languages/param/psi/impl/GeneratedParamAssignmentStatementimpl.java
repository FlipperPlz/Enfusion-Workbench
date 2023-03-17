// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins.ParamAssignmentMixin;
import com.flipperplz.enfusionWorkbench.languages.param.psi.*;

public class GeneratedParamAssignmentStatementimpl extends ParamAssignmentMixin implements GeneratedParamAssignmentStatement {

  public GeneratedParamAssignmentStatementimpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitAssignmentStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GeneratedParamVisitor) accept((GeneratedParamVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public GeneratedParamIdentifier getIdentifier() {
    return findNotNullChildByClass(GeneratedParamIdentifier.class);
  }

  @Override
  @Nullable
  public GeneratedParamLiteralArray getLiteralArray() {
    return findChildByClass(GeneratedParamLiteralArray.class);
  }

  @Override
  @Nullable
  public GeneratedParamParamNumeric getParamNumeric() {
    return findChildByClass(GeneratedParamParamNumeric.class);
  }

  @Override
  @Nullable
  public GeneratedParamParamString getParamString() {
    return findChildByClass(GeneratedParamParamString.class);
  }

  @Override
  @Nullable
  public PsiElement getOpAddAssign() {
    return findChildByType(OP_ADD_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getOpAssign() {
    return findChildByType(OP_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getOpSubAssign() {
    return findChildByType(OP_SUB_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getSymLsbracket() {
    return findChildByType(SYM_LSBRACKET);
  }

  @Override
  @Nullable
  public PsiElement getSymRsbracket() {
    return findChildByType(SYM_RSBRACKET);
  }

  @Override
  @Nullable
  public PsiElement getSymSemicolon() {
    return findChildByType(SYM_SEMICOLON);
  }

}
