package com.flipperplz.enfusionWorkbench.languages.param.inspections.errors.textures

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamNumericLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamProceduralTexture
import com.flipperplz.enfusionWorkbench.languages.param.psi.toNumber
import com.flipperplz.enfusionWorkbench.languages.param.quickfixes.ReplaceIdentifierLiteralQuickFix
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamProceduralTextureFormat
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType

class ParamIncorrectFormatException : AbstractBaseParamTextureLocalInspectionTool() {
    override fun getDescriptionFileName(): String = "ParamIncorrectFormat.html"
    override fun getShortName(): String = "ParamIncorrectFormat"
    override fun isEnabledByDefault(): Boolean = true
    override fun checkTexture(
        texture: ParamProceduralTexture,
        manager: InspectionManager,
        onTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        if(texture.format != null && ParamProceduralTextureFormat.fromIdentifier(texture.format) == null) {
            val format = texture.format!!
            return arrayOf(
                manager.createProblemDescriptor(
                    format,
                    "ParamIncorrectFormat.html",
                    onTheFly,
                    arrayOf(
                        ReplaceIdentifierLiteralQuickFix(format, ParamProceduralTextureFormat.ARGB.text),
                        ReplaceIdentifierLiteralQuickFix(format, ParamProceduralTextureFormat.RGB.text),
                        ReplaceIdentifierLiteralQuickFix(format, ParamProceduralTextureFormat.A.text),
                        ReplaceIdentifierLiteralQuickFix(format, ParamProceduralTextureFormat.I.text),
                        ReplaceIdentifierLiteralQuickFix(format, ParamProceduralTextureFormat.AI.text),
                    ),
                    ProblemHighlightType.GENERIC_ERROR
                )
            )
        }

        return super.checkTexture(texture, manager, onTheFly)
    }
}


