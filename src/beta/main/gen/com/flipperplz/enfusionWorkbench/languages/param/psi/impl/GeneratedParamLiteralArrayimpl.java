// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins.ParamArrayMixin;
import com.flipperplz.enfusionWorkbench.languages.param.psi.*;

public class GeneratedParamLiteralArrayimpl extends ParamArrayMixin implements GeneratedParamLiteralArray {

  public GeneratedParamLiteralArrayimpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitLiteralArray(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GeneratedParamVisitor) accept((GeneratedParamVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GeneratedParamLiteralArray> getLiteralArrayList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamLiteralArray.class);
  }

  @Override
  @NotNull
  public List<GeneratedParamParamNumeric> getParamNumericList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamParamNumeric.class);
  }

  @Override
  @NotNull
  public List<GeneratedParamParamString> getParamStringList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamParamString.class);
  }

  @Override
  @NotNull
  public PsiElement getSymLbracket() {
    return findNotNullChildByType(SYM_LBRACKET);
  }

  @Override
  @Nullable
  public PsiElement getSymRbracket() {
    return findChildByType(SYM_RBRACKET);
  }

}
