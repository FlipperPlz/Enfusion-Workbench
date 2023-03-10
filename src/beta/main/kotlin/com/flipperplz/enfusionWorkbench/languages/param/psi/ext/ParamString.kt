package com.flipperplz.enfusionWorkbench.languages.param.psi.ext

interface ParamString : ParamLiteral {
    override val isNumeric: Boolean get() = false
    val quotationType: ParamStringQuoteType?
        get() = ParamStringQuoteType.getStringType(text)

    enum class ParamStringQuoteType(
        val escapeString: String,
        val quoteChar: Char
    ) {
        SINGLE("''", '\''),
        DOUBLE("\"\"", '"');

        fun asUnquotedString(string: String): String = string.removePrefix(quoteChar.toString()).removeSuffix(quoteChar.toString())

        fun asUnquotedEscaped(string: String): String = escapeString(asUnquotedString(string))

        fun escapeString(string: String): String = string.replace(escapeString, quoteChar.toString())

        companion object {
            fun getStringType(string: String): ParamStringQuoteType? {
                for(type in ParamStringQuoteType.values()) {
                    if(string.startsWith(type.quoteChar) && string.endsWith(type.quoteChar)) return type
                }
                return null
            }
        }
    }
}