package com.flipperplz.enfusionWorkbench.sdk.reforger

import com.intellij.openapi.projectRoots.AdditionalDataConfigurable
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.projectRoots.SdkModel
import com.intellij.openapi.projectRoots.SdkModificator
import com.intellij.openapi.projectRoots.SdkType
import org.jdom.Element

class ArmaRFSdkType : SdkType("Arma Reforger"){
    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        TODO("Not yet implemented")
    }

    override fun suggestHomePath(): String? {
        TODO("Not yet implemented")
    }

    override fun isValidSdkHome(path: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String {
        TODO("Not yet implemented")
    }

    override fun createAdditionalDataConfigurable(
        sdkModel: SdkModel,
        sdkModificator: SdkModificator
    ): AdditionalDataConfigurable? {
        TODO("Not yet implemented")
    }

    override fun getPresentableName(): String {
        TODO("Not yet implemented")
    }
}