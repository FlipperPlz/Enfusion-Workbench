package com.flipperplz.bisutils.rap

typealias BisRapArrayElement = BisRapElement.BisRapLiteral<*>
typealias BisRapParameterValue = BisRapElement.BisRapLiteral.BisRapParameterValue<*>
typealias BisRapStringLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapString
typealias BisRapIntegerLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapNumeric.BisRapInteger
typealias BisRapFloatLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapNumeric.BisRapFloat
typealias BisRapDoubleLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapNumeric.BisRapDouble
typealias BisRapNumericLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapNumeric<*>
typealias BisRapBaseParameterStatement = BisRapElement.BisRapStatement.BisRapTokenStatement<*>
typealias BisRapBaseArrayStatement = BisRapElement.BisRapStatement.BisRapTokenStatement.BisRapArrayStatementBase
typealias BisRapArrayLiteral = BisRapElement.BisRapLiteral.BisRapArray
typealias BisRapClassStatement = BisRapElement.BisRapStatement.BisRapBaseClassStatement.BisRapRegularClassStatement
typealias BisRapExternalClassStatement = BisRapElement.BisRapStatement.BisRapBaseClassStatement.BisRapExternalClassStatement
typealias BisRapDeleteStatement = BisRapElement.BisRapStatement.BisRapDeleteStatement
typealias BisRapParameterStatement = BisRapElement.BisRapStatement.BisRapTokenStatement.BisRapValueAssignStatement
typealias BisRapArrayStatement = BisRapElement.BisRapStatement.BisRapTokenStatement.BisRapArrayStatementBase.BisRapArrayAssignStatement
typealias BisRapArrayAddStatement = BisRapElement.BisRapStatement.BisRapTokenStatement.BisRapArrayStatementBase.BisRapArrayAddStatement
typealias BisRapArraySubtractStatement = BisRapElement.BisRapStatement.BisRapTokenStatement.BisRapArrayStatementBase.BisRapArraySubtractStatement
typealias BisRapFile = BisRapElement.BisRapFile

sealed interface BisRapElement {
    val parentElement: BisRapElement?

    sealed interface BisRapStatementHolder : BisRapElement { val statements: List<BisRapStatement> }

    sealed class BisRapLiteral<T>(override val parentElement: BisRapStatement, val value: T) : BisRapElement {
        sealed class BisRapParameterValue<T>(parentElement: BisRapStatement, value: T) :  BisRapLiteral<T>(parentElement, value) {
            class BisRapString(parentElement: BisRapStatement, value: String) : BisRapLiteral<String>(parentElement, value)

            sealed class BisRapNumeric<T : Number>(parentElement: BisRapStatement, value: T) : BisRapLiteral<T>(parentElement, value) {
                class BisRapInteger(parentElement: BisRapStatement, value: Int) : BisRapNumeric<Int>(parentElement, value)

                class BisRapFloat(parentElement: BisRapStatement, value: Float) : BisRapNumeric<Float>(parentElement, value)

                class BisRapDouble(parentElement: BisRapStatement, value: Double) : BisRapNumeric<Double>(parentElement, value)
            }
        }

        class BisRapArray(parentElement: BisRapStatement, value: List<BisRapArrayElement>) :  BisRapLiteral<List<BisRapArrayElement>>(parentElement, value)
    }

    sealed class BisRapStatement(override val parentElement: BisRapElement?) : BisRapElement {
        sealed class BisRapBaseClassStatement(parentElement: BisRapElement?, val classname: String) : BisRapStatement(parentElement) {
            class BisRapExternalClassStatement(
                parentElement: BisRapElement?, classname: String
            ) : BisRapBaseClassStatement(parentElement, classname)

            class BisRapRegularClassStatement(
                parentElement: BisRapElement?, classname: String, val superclass: String, override val statements: List<BisRapStatement>
            ) : BisRapBaseClassStatement(parentElement, classname), BisRapStatementHolder
        }

        sealed class BisRapTokenStatement<T : BisRapLiteral<*>>(parentElement: BisRapElement?, val tokenName: String, val tokenValue: T) : BisRapStatement(parentElement) {
            class BisRapValueAssignStatement(
                parentElement: BisRapElement?, tokenName: String, tokenValue: BisRapParameterValue
            ) : BisRapTokenStatement<BisRapParameterValue>(parentElement, tokenName, tokenValue)

            sealed class BisRapArrayStatementBase(parentElement: BisRapElement?, tokenName: String, tokenValue: BisRapLiteral.BisRapArray) : BisRapTokenStatement<BisRapLiteral.BisRapArray>(parentElement, tokenName, tokenValue) {
                class BisRapArrayAssignStatement(
                    parentElement: BisRapElement?, tokenName: String, tokenValue: BisRapLiteral.BisRapArray
                ) : BisRapArrayStatementBase(parentElement, tokenName, tokenValue)

                class BisRapArrayAddStatement(
                    parentElement: BisRapElement?, tokenName: String, tokenValue: BisRapLiteral.BisRapArray
                ) : BisRapArrayStatementBase(parentElement, tokenName, tokenValue)

                class BisRapArraySubtractStatement(
                    parentElement: BisRapElement?, tokenName: String, tokenValue: BisRapLiteral.BisRapArray
                ) : BisRapArrayStatementBase(parentElement, tokenName, tokenValue)
            }
        }

        class BisRapDeleteStatement(
            parentElement: BisRapElement?,
            val target: String
        ) : BisRapStatement(parentElement)
    }

    class BisRapFile(
        override val statements: List<BisRapStatement>,
        val enums: Map<String, Int>
    ) : BisRapStatementHolder {
        override val parentElement: BisRapElement? = null
    }
}
