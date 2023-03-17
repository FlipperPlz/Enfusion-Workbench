package com.flipperplz.enfusionWorkbench.languages.param.psi

interface ParamFile : ParamScope {
    override fun getPreviousScope(): ParamScope? = null
    fun getParamName(): String
    fun isRVMAT(): Boolean
    fun isWorkshopConfig(): Boolean
    fun isPBOConfig(): Boolean
    fun hasBinExtension(): Boolean

}