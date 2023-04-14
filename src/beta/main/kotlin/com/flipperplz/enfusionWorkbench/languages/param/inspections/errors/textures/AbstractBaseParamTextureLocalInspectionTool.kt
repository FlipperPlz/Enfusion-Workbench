package com.flipperplz.enfusionWorkbench.languages.param.inspections.errors.textures

import com.flipperplz.enfusionWorkbench.languages.param.inspections.AbstractBaseParamLocalInspectionTool
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamProceduralTexture
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.asProceduralTexture
import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemDescriptor

abstract class AbstractBaseParamTextureLocalInspectionTool : AbstractBaseParamLocalInspectionTool() {
    override fun checkLiteral(
        literal: ParamLiteral,
        manager: InspectionManager,
        isOnTheFly: Boolean
    ): Array<ProblemDescriptor>? {
        if(literal is ParamStringLiteral) {
            literal.asProceduralTexture()?.let {
                return checkTexture(it, manager, isOnTheFly)
            }
        }
        return super.checkLiteral(literal, manager, isOnTheFly)
    }

    open fun checkTexture(texture: ParamProceduralTexture, manager: InspectionManager, onTheFly: Boolean): Array<ProblemDescriptor>? = null

}