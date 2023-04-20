package com.flipperplz.bisutils.rap.io

sealed class BisRapBeautifier(private var currentIndentation: Int?) {
    val indent: () -> String
        get() = ::createIndentation
    val popIndent: () -> String
        get() {
            popIndentation()
            return ::createIndentation
        }

    fun popIndentation() {
        currentIndentation?.let {
            currentIndentation = it - 1
        }
    }

    fun pushIndentation() {
        currentIndentation?.let {
            currentIndentation = it + 1
        }
    }

    private fun createIndentation(): String = if (currentIndentation == null) "" else "\t".repeat(currentIndentation!!)

    object OFF : BisRapBeautifier(null)
    class ON(currentIndentation: Int) : BisRapBeautifier(currentIndentation)
}