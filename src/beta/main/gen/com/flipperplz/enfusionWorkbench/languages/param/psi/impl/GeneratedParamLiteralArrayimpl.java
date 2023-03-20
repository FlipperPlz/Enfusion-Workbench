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

  public GeneratedParamLiteralArrayimpl(ASTNode node) {
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
  public List<GeneratedParamArrayElement> getArrayElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GeneratedParamArrayElement.class);
  }

  @Override
  @NotNull
  public PsiElement getSymLcurly() {
    return findNotNullChildByType(SYM_LCURLY);
  }

  @Override
  @Nullable
  public PsiElement getSymRcurly() {
    return findChildByType(SYM_RCURLY);
  }

}
