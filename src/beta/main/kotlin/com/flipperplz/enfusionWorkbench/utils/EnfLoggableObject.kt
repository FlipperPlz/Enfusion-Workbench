package com.flipperplz.enfusionWorkbench.utils

import com.intellij.openapi.diagnostic.Logger;

abstract class EnfLoggableObject(clazz: Class<*>) {
    protected val LOG: Logger = Logger.getInstance(clazz)
}