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

public class GeneratedParamEnumDeclarationimpl extends ParamElementImpl implements GeneratedParamEnumDeclaration {

  public GeneratedParamEnumDeclarationimpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GeneratedParamVisitor visitor) {
    visitor.visitEnumDeclaration(this);
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
  public PsiElement getKeywordEnum() {
    return findNotNullChildByType(KEYWORD_ENUM);
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

  @Override
  @Nullable
  public PsiElement getSymSemicolon() {
    return findChildByType(SYM_SEMICOLON);
  }

}
