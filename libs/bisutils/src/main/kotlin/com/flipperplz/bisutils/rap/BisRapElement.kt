package com.flipperplz.bisutils.rap

typealias BisRapArrayElement = BisRapElement.BisRapLiteral<*>
typealias BisRapParameterValue = BisRapElement.BisRapLiteral.BisRapParameterValue<*>
typealias BisRapStringLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapString
typealias BisRapIntegerLiteral = BisRapElement.BisRapLiteral.BisRapParameterValue.BisRapNumeric.BisRapInteger
typealias BisRapStatement = BisRapElement.BisRapStatement
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
    val children: List<BisRapElement>?
        get() = null

    sealed interface BisRapStatementHolder : BisRapElement {
        val statements: MutableList<BisRapStatement>
    }

    sealed class BisRapLiteral<T>(override val parentElement: BisRapElement) : BisRapElement {
        abstract val value: T
        sealed class BisRapParameterValue<T>(parentElement: BisRapElement, override val value: T) :  BisRapLiteral<T>(parentElement) {
            class BisRapString(parentElement: BisRapElement, value: String) : BisRapParameterValue<String>(parentElement, value);

            sealed class BisRapNumeric<T : Number>(parentElement: BisRapElement, value: T) : BisRapParameterValue<T>(parentElement, value) {
                override val children: List<BisRapElement>? = null

                class BisRapInteger(parentElement: BisRapElement, value: Int) : BisRapNumeric<Int>(parentElement, value)

                class BisRapFloat(parentElement: BisRapElement, value: Float) : BisRapNumeric<Float>(parentElement, value)

                class BisRapDouble(parentElement: BisRapElement, value: Double) : BisRapNumeric<Double>(parentElement, value)
            }
        }

        class BisRapArray(parentElement: BisRapElement) :  BisRapLiteral<MutableList<BisRapArrayElement>>(parentElement) {
            override val value: MutableList<BisRapArrayElement> = mutableListOf()

            override val children: List<BisRapElement>
                get() = value
        }
    }

    sealed class BisRapStatement(override val parentElement: BisRapElement?) : BisRapElement {
        sealed class BisRapBaseClassStatement(parentElement: BisRapElement?, val classname: String) : BisRapStatement(parentElement) {
            class BisRapExternalClassStatement(
                parentElement: BisRapElement?, classname: String
            ) : BisRapBaseClassStatement(parentElement, classname)

            class BisRapRegularClassStatement(
                parentElement: BisRapElement?, val binaryOffset: Int, classname: String
            ) : BisRapBaseClassStatement(parentElement, classname), BisRapStatementHolder {
                override val children: List<BisRapElement> by lazy { statements }
                override val statements: MutableList<BisRapStatement> = mutableListOf()
                var superclass: String = ""
            }
        }

        sealed class BisRapTokenStatement<T : BisRapLiteral<*>>(parentElement: BisRapElement?, val tokenName: String) : BisRapStatement(parentElement) {
            abstract val tokenValue: T
            override val children: List<T> by lazy { listOf(tokenValue) }

            class BisRapValueAssignStatement(
                parentElement: BisRapElement?, tokenName: String
            ) : BisRapTokenStatement<BisRapLiteral<*>>(parentElement, tokenName) {
                override lateinit var tokenValue: BisRapLiteral<*>
            }

            sealed class BisRapArrayStatementBase(parentElement: BisRapElement?, tokenName: String) : BisRapTokenStatement<BisRapLiteral.BisRapArray>(parentElement, tokenName) {
                override lateinit var tokenValue: BisRapLiteral.BisRapArray

                class BisRapArrayAssignStatement(
                    parentElement: BisRapElement?, tokenName: String
                ) : BisRapArrayStatementBase(parentElement, tokenName)

                class BisRapArrayAddStatement(
                    parentElement: BisRapElement?, tokenName: String
                ) : BisRapArrayStatementBase(parentElement, tokenName)

                class BisRapArraySubtractStatement(
                    parentElement: BisRapElement?, tokenName: String
                ) : BisRapArrayStatementBase(parentElement, tokenName)
            }
        }

        class BisRapDeleteStatement(
            parentElement: BisRapElement?,
            val target: String
        ) : BisRapStatement(parentElement)
    }

    class BisRapFile : BisRapStatementHolder {
        override val parentElement: BisRapElement? = null
        override val children: List<BisRapElement>
            get() = statements

        override val statements: MutableList<BisRapStatement> = mutableListOf()
        lateinit var enums: Map<String, Int>
    }
}
