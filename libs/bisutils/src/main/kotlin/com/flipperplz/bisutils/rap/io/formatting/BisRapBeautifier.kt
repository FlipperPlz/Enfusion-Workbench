package com.flipperplz.bisutils.rap.io.formatting

sealed class BisRapBeautifier(protected var currentIndentation: Int = 0) {
    protected companion object {
        protected const val NEW_LINE = '\n'
        protected const val TAB = '\t'
        protected const val SPACE = ' '
    }

    fun indent(builder: StringBuilder) {
        val indentCount = currentIndentation
        for (i in 1..indentCount) {
            builder.append(TAB)
        }
    }

    abstract fun beforeDeleteKw(builder: StringBuilder)
    abstract fun beforeClassKw(builder: StringBuilder)
    abstract fun beforeVariableName(builder: StringBuilder)
    abstract fun afterClassStatement(builder: StringBuilder)
    abstract fun afterExternalClassStatement(builder: StringBuilder)
    abstract fun afterDeleteStatement(builder: StringBuilder)
    abstract fun afterArrayStatement(builder: StringBuilder)
    abstract fun afterVariableStatement(builder: StringBuilder)
    abstract fun classKw2Classname(builder: StringBuilder)
    abstract fun classname2Semi(builder: StringBuilder)
    abstract fun classname2Colon(builder: StringBuilder)
    abstract fun colon2SuperName(builder: StringBuilder)
    abstract fun superName2LCurly(builder: StringBuilder)
    abstract fun className2LCurly(builder: StringBuilder)
    abstract fun lCurly2ClassBody(builder: StringBuilder)
    abstract fun classBody2RCurly(builder: StringBuilder)
    abstract fun rCurly2Semi(builder: StringBuilder)
    abstract fun deleteKw2DeleteTarget(builder: StringBuilder)
    abstract fun deleteTarget2Semi(builder: StringBuilder)
    abstract fun arrayName2LSquare(builder: StringBuilder)
    abstract fun lSquare2RSquare(builder: StringBuilder)
    abstract fun rSquare2Operator(builder: StringBuilder)
    abstract fun operator2ArrayStart(builder: StringBuilder)
    abstract fun arrayStart2ArrayElement(builder: StringBuilder)
    abstract fun arrayElement2Comma(builder: StringBuilder)
    abstract fun comma2ArrayElement(builder: StringBuilder)
    abstract fun arrayElement2ArrayEnd(builder: StringBuilder)
    abstract fun arrayEnd2Semi(builder: StringBuilder)
    abstract fun varName2Operator(builder: StringBuilder)
    abstract fun operator2VariableValue(builder: StringBuilder)
    abstract fun variableValue2Semi(builder: StringBuilder)

    object NONE : BisRapBeautifier(0) {
        override fun beforeDeleteKw(builder: StringBuilder) = Unit
        override fun beforeClassKw(builder: StringBuilder) = Unit
        override fun beforeVariableName(builder: StringBuilder) = Unit
        override fun afterClassStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterExternalClassStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterDeleteStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterArrayStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterVariableStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun classKw2Classname(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun classname2Semi(builder: StringBuilder) = Unit
        override fun classname2Colon(builder: StringBuilder) = Unit
        override fun colon2SuperName(builder: StringBuilder) = Unit
        override fun superName2LCurly(builder: StringBuilder) = Unit
        override fun className2LCurly(builder: StringBuilder) = Unit
        override fun lCurly2ClassBody(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun classBody2RCurly(builder: StringBuilder) = Unit
        override fun rCurly2Semi(builder: StringBuilder) = Unit
        override fun deleteKw2DeleteTarget(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun deleteTarget2Semi(builder: StringBuilder) = Unit
        override fun arrayName2LSquare(builder: StringBuilder) = Unit
        override fun lSquare2RSquare(builder: StringBuilder) = Unit
        override fun rSquare2Operator(builder: StringBuilder) = Unit
        override fun operator2ArrayStart(builder: StringBuilder) = Unit
        override fun arrayStart2ArrayElement(builder: StringBuilder) = Unit
        override fun arrayElement2Comma(builder: StringBuilder) = Unit
        override fun comma2ArrayElement(builder: StringBuilder) = Unit
        override fun arrayElement2ArrayEnd(builder: StringBuilder) = Unit
        override fun arrayEnd2Semi(builder: StringBuilder) = Unit
        override fun varName2Operator(builder: StringBuilder) = Unit
        override fun operator2VariableValue(builder: StringBuilder) = Unit
        override fun variableValue2Semi(builder: StringBuilder) = Unit
    }

    object K_R : BisRapBeautifier(0) {
        override fun afterClassStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterExternalClassStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterDeleteStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterArrayStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun afterVariableStatement(builder: StringBuilder) = builder.append(NEW_LINE).let { Unit }
        override fun classKw2Classname(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun beforeDeleteKw(builder: StringBuilder) = indent(builder)
        override fun beforeClassKw(builder: StringBuilder) = indent(builder)
        override fun beforeVariableName(builder: StringBuilder) = indent(builder)
        override fun classname2Semi(builder: StringBuilder) = Unit
        override fun classname2Colon(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun colon2SuperName(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun superName2LCurly(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun className2LCurly(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun lCurly2ClassBody(builder: StringBuilder) = builder.append(NEW_LINE).let { currentIndentation++; Unit }
        override fun classBody2RCurly(builder: StringBuilder) = builder.let { currentIndentation--; indent(it); Unit }
        override fun rCurly2Semi(builder: StringBuilder) = Unit
        override fun deleteKw2DeleteTarget(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun deleteTarget2Semi(builder: StringBuilder) = Unit
        override fun arrayName2LSquare(builder: StringBuilder) = Unit
        override fun lSquare2RSquare(builder: StringBuilder) = Unit
        override fun rSquare2Operator(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun operator2ArrayStart(builder: StringBuilder) = Unit
        override fun arrayStart2ArrayElement(builder: StringBuilder) {  currentIndentation++; builder.append(NEW_LINE); return Unit}
        override fun arrayElement2Comma(builder: StringBuilder) = Unit
        override fun comma2ArrayElement(builder: StringBuilder) = indent(builder.append(NEW_LINE))
        override fun arrayElement2ArrayEnd(builder: StringBuilder) = builder.append(NEW_LINE).let { currentIndentation--; indent(it) }
        override fun arrayEnd2Semi(builder: StringBuilder) = Unit
        override fun varName2Operator(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun operator2VariableValue(builder: StringBuilder) = builder.append(SPACE).let { Unit }
        override fun variableValue2Semi(builder: StringBuilder) = Unit
    }

}
