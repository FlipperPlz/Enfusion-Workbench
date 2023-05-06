package com.flipperplz.enfusionWorkbench.sdk.param.wrappers

import com.flipperplz.bisutils.param.slim.ParamSlim
import com.flipperplz.bisutils.param.slim.ParamSlimArray
import com.flipperplz.bisutils.param.slim.ParamSlimFloat
import com.flipperplz.bisutils.param.slim.ParamSlimString
import com.flipperplz.bisutils.param.slim.impl.literal.ParamSlimArrayImpl
import com.flipperplz.bisutils.param.slim.impl.literal.ParamSlimFloatImpl
import com.flipperplz.bisutils.param.slim.util.ParamMappableClass


//TODO(bisutils-kt): Add Functions for ParamMappableClass::Serialize and ParamMappableClass::Deserialize
class DZCfgPatches(
    override var parentElement: ParamSlim?,
    className: String,
    @PMappedVariableValue("addonRootClass")
    val addonRootClass: ParamSlimString? = null,

    @PMappedVariableValue("requiredVersion")
    val requiredVersion: ParamSlimFloat? = ParamSlimFloatImpl(null, 0.1F),

    @PMappedVariableValue("requiredAddons")
    val requiredAddons: ParamSlimArray = ParamSlimArrayImpl(null),

    @PMappedVariableValue("units")
    val units: ParamSlimArray = ParamSlimArrayImpl(null),

    @PMappedVariableValue("weapons")
    val weapons: ParamSlimArray = ParamSlimArrayImpl(null)
) : ParamMappableClass(className, "");