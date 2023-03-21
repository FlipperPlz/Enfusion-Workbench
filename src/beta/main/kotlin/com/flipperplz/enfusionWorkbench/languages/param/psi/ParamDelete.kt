package com.flipperplz.enfusionWorkbench.languages.param.psi


interface ParamDelete : ParamStatement {
    val paramDeleting: ParamIdentifier
    override val binarizable: Boolean
        get() = true

    override fun asParsableText(): String = "delete ${paramDeleting.text};"
}