package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

interface ParamArray : ParamArrayElement {
    companion object {
        fun formattedArray(element: List<ParamArrayElement>): String = "{${element.joinToString(", ") { it.writeAsParam() }}}"

        fun formattedArray(element: List<String>): String = "{${element.joinToString(", ")}}"
    }

}