package com.flipperplz.enfusionWorkbench.languages.param.psi

sealed interface ParamSlim { var parentElement: ParamSlim? }
sealed interface ParamSlimCommand : ParamSlim
sealed interface ParamSlimLiteral<T> : ParamSlim { var value: T }
sealed interface ParamSlimNumericLiteral<T: Number> : ParamSlimLiteral<T>
interface ParamSlimString : ParamSlimLiteral<String>
interface ParamSlimFloat : ParamSlimNumericLiteral<Float>
interface ParamSlimInt : ParamSlimNumericLiteral<Int>
interface ParamSlimArray : ParamSlimLiteral<MutableList<ParamSlimLiteral<*>>>
interface ParamSlimExternalClass : ParamSlimCommand { var className: String }
interface ParamSlimClass : ParamSlimExternalClass { var superClass: String?; val commands: MutableList<ParamSlimCommand> }
interface ParamSlimDeleteStatement : ParamSlimCommand { var target: String }
interface ParamSlimVariableStatement : ParamSlimCommand { var name: String; var value: ParamSlimLiteral<*> }

data class ParamSlimStringImpl(
    override var parentElement: ParamSlim?,
    override var value: String
) : ParamSlim, ParamSlimString

data class ParamSlimFloatImpl(
    override var parentElement: ParamSlim?,
    override var value: Float
) : ParamSlim, ParamSlimFloat

data class ParamSlimIntImpl(
    override var parentElement: ParamSlim?,
    override var value: Int
) : ParamSlim, ParamSlimInt

data class ParamSlimArrayImpl(
    override var parentElement: ParamSlim?,
    override var value: MutableList<ParamSlimLiteral<*>> = mutableListOf()
) : ParamSlim, ParamSlimArray

data class ParamSlimExternalClassImpl(
    override var parentElement: ParamSlim?,
    override var className: String
) : ParamSlimExternalClass

data class ParamSlimClassImpl(
    override var parentElement: ParamSlim?,
    override var className: String,
    override var superClass: String?,
    override val commands: MutableList<ParamSlimCommand>
): ParamSlimClass

data class ParamSlimDeleteStatementImpl(
    override var parentElement: ParamSlim?,
    override var target: String
) : ParamSlimDeleteStatement

data class ParamSlimVariableStatementImpl(
    override var parentElement: ParamSlim?,
    override var name: String,
    override var value: ParamSlimLiteral<*>
) : ParamSlimVariableStatement
