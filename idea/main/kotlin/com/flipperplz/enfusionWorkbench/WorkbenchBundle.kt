package com.flipperplz.enfusionWorkbench

import com.intellij.DynamicBundle
import org.jetbrains.annotations.PropertyKey

object WorkbenchBundle : DynamicBundle(WorkbenchConstants.BUNDLE_PATH) {

    @JvmStatic
    fun message(
        @PropertyKey(resourceBundle = WorkbenchConstants.BUNDLE_PATH) key: String,
        vararg params: Any
    ) = getMessage(key, *params)

    @JvmStatic
    fun messagePointer(
        @PropertyKey(resourceBundle = WorkbenchConstants.BUNDLE_PATH) key: String,
        vararg params: Any
    ) = getLazyMessage(key, *params)

}