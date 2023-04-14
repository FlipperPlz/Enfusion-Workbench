package com.flipperplz.enfusionWorkbench.languages.param.inspections.errors.textures

import com.flipperplz.enfusionWorkbench.languages.param.inspections.AbstractBaseParamLocalInspectionTool
import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.flipperplz.enfusionWorkbench.languages.param.quickfixes.ReplaceNumericLiteralQuickFix
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalQuickFixOnPsiElement
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class ParamIncorrectPowerInspection : AbstractBaseParamTextureLocalInspectionTool() {


    override fun getDescriptionFileName(): String = "InvalidTextureDimension.html"
    override fun getShortName(): String = "Texture Dimensions Must Be A Power Of 2"
    override fun isEnabledByDefault(): Boolean = true
    override fun checkTexture(
        texture: ParamProceduralTexture,
        manager: InspectionManager,
        onTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        fun isValid(n: Number?): Boolean = if(n == null) false else (n is Comparable<*> && n.toDouble() > 0 && (n.toLong() and (n.toLong() - 1)) == 0L)

        //time complexity O(log n)
        fun closestPowerOfTwo(n: Double): Int {
            if (n <= 0) return 0
            if (n >= 200000000) return 2
            var power = 1
            while (power < n) power *= 2

            return if ((n - power / 2) < (power - n)) power / 2 else power
        }

        fun checkDimension(literal: ParamNumericLiteral?): ProblemDescriptor? {
            val number = literal?.toNumber() ?: return null
            if (isValid(number)) return null
            val replacement = closestPowerOfTwo(number.toDouble())
            return manager.createProblemDescriptor(
                literal,
                "InvalidTextureDimensions.html",
                ReplaceNumericLiteralQuickFix(literal, replacement),
                ProblemHighlightType.GENERIC_ERROR,
                onTheFly
            )
        }

        val descriptors = arrayListOf<ProblemDescriptor>()
        checkDimension(texture.width)?.let { descriptors.add(it) }
        checkDimension(texture.height)?.let { descriptors.add(it) }
        return if (descriptors.isNotEmpty()) descriptors.toTypedArray() else super.checkTexture(texture, manager, onTheFly)
    }
}


