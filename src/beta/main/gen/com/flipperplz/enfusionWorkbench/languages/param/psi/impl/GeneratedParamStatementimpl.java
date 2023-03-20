// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import com.flipperplz.enfusionWorkbench.languages.param.psi.*;

public class GeneratedParamStatementimpl extends ParamElementImpl implements GeneratedParamStatement {

  public GeneratedParamStatementimpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GeneratedParamVisitor) accept((GeneratedParamVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GeneratedParamAssignmentStatement getAssignmentStatement() {
    return findChildByClass(GeneratedParamAssignmentStatement.class);
  }

  @Override
  @Nullable
  public GeneratedParamClassDeclaration getClassDeclaration() {
    return findChildByClass(GeneratedParamClassDeclaration.class);
  }

  @Override
  @Nullable
  public GeneratedParamDeleteStatement getDeleteStatement() {
    return findChildByClass(GeneratedParamDeleteStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getSymSemi() {
    return findChildByType(SYM_SEMI);
  }

}
