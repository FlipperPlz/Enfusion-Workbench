package com.flipperplz.enfusionWorkbench.languages.param.annotator

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamStringLiteralImpl
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class TextureStringAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if(element is ParamStringLiteralImpl) {
            val stringContents = element.stringContents
            if(!stringContents.startsWith('#')) return

        }
    }
}