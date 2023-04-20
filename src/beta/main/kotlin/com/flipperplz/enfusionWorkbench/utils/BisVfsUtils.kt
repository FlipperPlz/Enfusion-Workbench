package com.flipperplz.enfusionWorkbench.utils

import com.intellij.openapi.vfs.VirtualFile

fun VirtualFile.findParamConfig(): VirtualFile? =
    findChild("config.cpp") ?: findChild("config.bin")