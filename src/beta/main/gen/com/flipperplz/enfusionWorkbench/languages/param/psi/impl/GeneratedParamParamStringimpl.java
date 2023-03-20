// This is a generated file. Not intended for manual editing.
package com.flipperplz.enfusionWorkbench.languages.param.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes.*;
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.mixins.ParamStringMixin;
import com.flipperplz.enfusionWorkbench.languages.param.psi.*;

public class GeneratedParamParamStringimpl extends ParamStringMixin implements GeneratedParamParamString {

  public GeneratedParamParamStringimpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitParamString(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GeneratedParamVisitor) accept((GeneratedParamVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getAbsString() {
    return findNotNullChildByType(ABS_STRING);
  }

}
