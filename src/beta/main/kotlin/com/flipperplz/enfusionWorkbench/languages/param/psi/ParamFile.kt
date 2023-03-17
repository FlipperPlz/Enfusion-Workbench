package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamFile : ParamScope {
    override val previousScope: ParamScope? get() = null

    val paramName: String
    fun isRVMAT(): Boolean
    fun isWorkshopConfig(): Boolean
    fun isPBOConfig(): Boolean
    fun hasBinExtension(): Boolean
}