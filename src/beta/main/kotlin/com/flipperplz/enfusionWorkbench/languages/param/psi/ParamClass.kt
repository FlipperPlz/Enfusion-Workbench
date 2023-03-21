package com.flipperplz.enfusionWorkbench.languages.param.psi


interface ParamClass : ParamNamedStatement, ParamScope {
    override val binarizable: Boolean get() = true
    val className: ParamIdentifier
    val superClass: ParamIdentifier?
    val isExternalClass: Boolean get() = !node.textContains('{')

    override fun asParsableText(): String {
        if(isExternalClass) return "class ${className};"
        return """
            class $className ${if(superClass != null) ": ${superClass?.text}" else "" } { 
            ${statements.joinToString(separator = "\n") { it.asParsableText() }}
            };
        """.trimIndent()
    }
}