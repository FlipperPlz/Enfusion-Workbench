package com.flipperplz.enfusionWorkbench.languages.param.folding

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamArray
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class ParamFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        for (clazz in PsiTreeUtil.findChildrenOfType(root, ParamClass::class.java)) {
            val lCurly = clazz.node.findChildByType(ParamTypes.SYM_LCURLY) ?: continue
            val rCurly = clazz.node.findChildByType(ParamTypes.SYM_RCURLY) ?: continue
            descriptors.add(FoldingDescriptor(clazz, TextRange.create(lCurly.startOffset, rCurly.startOffset + 2)))
        }

        for(array in PsiTreeUtil.findChildrenOfType(root, ParamArray::class.java)) {
            val lCurly = array.node.findChildByType(ParamTypes.SYM_LCURLY) ?: continue
            val rCurly = array.node.findChildByType(ParamTypes.SYM_RCURLY) ?: continue
            descriptors.add(FoldingDescriptor(array, TextRange.create(lCurly.startOffset, rCurly.startOffset + 2)))
        }

        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String = "{...};"

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}