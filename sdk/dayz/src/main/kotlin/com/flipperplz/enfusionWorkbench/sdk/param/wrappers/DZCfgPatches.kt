package com.flipperplz.enfusionWorkbench.sdk.param.wrappers

import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamMappableClass

class DZCfgPatches(
    override var parentElement: ParamSlim?,
    className: String,

    @PMappedVariableValue("addonRootClass")
    val addonRootClass: ParamSlimArray? = null,

    @PMappedVariableValue("name")
    val patchName: ParamSlimString = ParamSlimStringImpl(null, className),

    @PMappedVariableValue("requiredVersion")
    val requiredVersion: ParamSlimString = ParamSlimStringImpl(null, className),

    @PMappedVariableValue("requiredAddons")
    val requiredAddons: ParamSlimArray = ParamSlimArrayImpl(null)
) : ParamMappableClass(className, "");