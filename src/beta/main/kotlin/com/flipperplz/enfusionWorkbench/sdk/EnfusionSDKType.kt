package com.flipperplz.enfusionWorkbench.sdk

import com.intellij.openapi.projectRoots.SdkType

abstract class EnfusionSDKType(
    gameName: String,
    val steamGameId: Long,
    val steamToolsId: Long,
    val steamServerId: Long
) : SdkType("Enfusion SDK (${gameName})") {



}