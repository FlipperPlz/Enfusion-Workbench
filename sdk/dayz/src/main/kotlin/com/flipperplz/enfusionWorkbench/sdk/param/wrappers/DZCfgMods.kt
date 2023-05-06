package com.flipperplz.enfusionWorkbench.sdk.param.wrappers

import com.flipperplz.bisutils.param.slim.ParamSlim
import com.flipperplz.bisutils.param.slim.ParamSlimArray
import com.flipperplz.bisutils.param.slim.ParamSlimString
import com.flipperplz.bisutils.param.slim.impl.literal.ParamSlimStringImpl
import com.flipperplz.bisutils.param.slim.util.ParamMappableClass

class DZCfgMods(
    override var parentElement: ParamSlim?,
    className: String,
    superClass: String?,

    @PMappedVariableValue("type")
    val type: ParamSlimString = ParamSlimStringImpl(null, "mod"),
    @PMappedVariableValue("inputs")
    val modInputsPath: ParamSlimString? = null,
    @PMappedVariableValue("dependencies") @ParamArrayTemplate<ParamSlimString>
    val modDependencies: ParamSlimArray? = null,
    @PMappedCommand
    val definitions: DZCfgMods.DZDefs,
    @PMappedVariableValue("dir")
    val modDirectory: ParamSlimString?,
    @PMappedVariableValue("picture")
    val modPicturePath: ParamSlimString?,
    @PMappedVariableValue("creditsJson")
    val creditsJsonPath: ParamSlimString?,
    @PMappedVariableValue("versionPath")
    val creditsVersionPath: ParamSlimString?,

) : ParamMappableClass(className, superClass) {
    class DZDefs(
        override var parentElement: ParamSlim?,
        superClass: String?,
        @PMappedCommand @ParamClassnameOverrider("imageSets")
        val imageSets: FileDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("widgetStyles")
        val widgetStyles: FileDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("engineScriptModule")
        val engineScriptModule: ScriptDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("gameLibScriptModule")
        val gameLibScriptModule: ScriptDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("gameScriptModule")
        val gameScriptModule: ScriptDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("worldScriptModule")
        val worldScriptModule: ScriptDefinitions?,
        @PMappedCommand @ParamClassnameOverrider("missionScriptModule")
        val missionScriptModule: ScriptDefinitions?
    ): ParamMappableClass("defs", superClass) {
        open class FileDefinitions(
            override var parentElement: ParamSlim?,
            className: String,

            @PMappedVariableValue("files") @ParamArrayTemplate<ParamSlimString>
            val files: ParamSlimArray
        ): ParamMappableClass(className, null)

        class ScriptDefinitions(
            override var parentElement: ParamSlim?,
            className: String,

            @PMappedVariableValue("value")
            val entryFunction: ParamSlimString = ParamSlimStringImpl(null, ""),
            files: ParamSlimArray
        ): FileDefinitions(parentElement, className, files)
    }
}