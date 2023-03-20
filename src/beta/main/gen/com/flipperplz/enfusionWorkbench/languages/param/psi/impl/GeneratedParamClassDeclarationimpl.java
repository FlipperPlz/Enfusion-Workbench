// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins.ParamClassMixin;
import com.flipperplz.enfusionWorkbench.languages.param.psi.*;

public class GeneratedParamClassDeclarationimpl extends ParamClassMixin implements GeneratedParamClassDeclaration {

  public GeneratedParamClassDeclarationimpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitClassDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GeneratedParamVisitor) accept((GeneratedParamVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GeneratedParamIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamIdentifier.class);
  }

  @Override
  @NotNull
  public List<GeneratedParamStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamStatement.class);
  }

}
