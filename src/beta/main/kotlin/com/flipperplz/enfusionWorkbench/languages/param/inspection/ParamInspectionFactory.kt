package com.flipperplz.enfusionWorkbench.languages.param.inspection

import com.flipperplz.enfusionWorkbench.languages.param.inspection.ParamAmbiguousStringInspection
import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool

class ParamInspectionFactory : InspectionToolProvider {
    override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> = arrayOf(
        ParamAmbiguousStringInspection::class.java
    )
}