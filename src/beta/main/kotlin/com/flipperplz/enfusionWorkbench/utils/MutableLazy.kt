package com.flipperplz.enfusionWorkbench.utils

fun <T> erasableLazy(value: () -> T) = BisErasableLazy(value)
class BisErasableLazy<T>(private val initializer: () -> T) : Lazy<T> {
    private var cached: T? = null
    override val value: T
        get() = cached ?: initializer().also { cached = it } as T

    fun reset() { cached = null }

    fun reset(initial: T) { cached = initial }
    override fun isInitialized(): Boolean = cached != null
}