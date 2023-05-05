package com.flipperplz.enfusionWorkbench.languages.param.utils

import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.util.containers.addIfNotNull
import kotlin.reflect.KCallable
import kotlin.reflect.full.*
import kotlin.reflect.typeOf

abstract class ParamMappableClass(
    override var className: String,
    override var superClass: String?
): ParamSlimClass {
    private val annotatedProperties: Collection<KCallable<*>>
    init {
        annotatedProperties = this::class.members
            .filterIsInstance<KCallable< ParamSlim>>()
            .filter { it.annotations.any { ann -> ann is PMappedVariable || ann is PMappedClass || ann is PMappedVariableValue } }
        remap()
    }
    @Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
    annotation class PMappedVariable()

    @Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
    annotation class PMappedClass()

    @Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
    annotation class PMappedVariableValue(val name: String)

    override val commands: MutableList<ParamSlimCommand> = mutableListOf()

    fun remap() {
        for (property in annotatedProperties) {
            val returnType = property.returnType
            when {
                returnType.isSubtypeOf(typeOf<ParamSlimCommand>()) -> commands.addIfNotNull(property.call() as ParamSlimCommand?)
                returnType.isSubtypeOf(typeOf<ParamSlimLiteral<*>>()) -> {
                    property.findAnnotation<PMappedVariableValue>()?.let {
                        (property.call() as ParamSlimLiteral<*>?)?.let { literal ->
                            commands.add(
                                ParamSlimVariableStatementImpl(
                                    this,
                                    it.name,
                                    literal
                                )
                            )
                        }

                    }
                }
            }
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}