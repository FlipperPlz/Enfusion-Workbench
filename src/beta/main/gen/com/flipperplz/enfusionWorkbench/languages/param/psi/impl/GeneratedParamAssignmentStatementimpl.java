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
  @Nullable
  public GeneratedParamArrayElement getArrayElement() {
    return findChildByClass(GeneratedParamArrayElement.class);
  }

  @Override
  @NotNull
  public GeneratedParamIdentifier getIdentifier() {
    return findNotNullChildByClass(GeneratedParamIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getOpAddassign() {
    return findChildByType(OP_ADDASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getOpAssign() {
    return findChildByType(OP_ASSIGN);
  }

  @Override
  @Nullable
  public PsiElement getOpSubassign() {
    return findChildByType(OP_SUBASSIGN);
  }

}
