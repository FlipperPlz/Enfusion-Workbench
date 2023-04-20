package com.flipperplz.enfusionWorkbench.sdk.dayz

import com.flipperplz.enfusionWorkbench.utils.findParamConfig
import com.flipperplz.enfusionWorkbench.vfs.pbo.PboFileType
import com.flipperplz.enfusionWorkbench.vfs.pbo.impl.PboFileSystem
import com.intellij.openapi.projectRoots.AdditionalDataConfigurable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.rd.util.first
import javax.swing.JComponent

data class DayZSdkAdditionalData(
    var gameHome: VirtualFile? = null,
    val dlcHomes: Map<String, VirtualFile> = emptyMap(),
    val masterPboName: String = "bin.pbo",
    val masterConfig: VirtualFile? = gameHome?.findChild(masterPboName)?.findParamConfig()
) : SdkAdditionalData

class DayZSdkAdditionalDataConfigurable : AdditionalDataConfigurable {
    private var modified = false
    private var additionalData: DayZSdkAdditionalData = DayZSdkAdditionalData()

    fun setGameHome(home: VirtualFile) {
        additionalData.gameHome = home
        modified = true
    }

    fun addDLC(dlcName: String, dlcHome: VirtualFile) {
        additionalData.dlcHomes.plus(dlcName to dlcHome)
        modified = true
    }

    fun removeDLC(dlcName: String) {
        additionalData.dlcHomes.minus(dlcName)
        modified = true
    }

    fun isFromDLC(file: VirtualFile): String? = with(PboFileSystem.instance.getLocalByEntry(file)?.parent) {
        if(this == null) return@with null
        return additionalData.dlcHomes.entries.find { it.value == this }?.key
    }

    override fun createComponent(): JComponent? {
        TODO("Not yet implemented")
    }

    override fun isModified(): Boolean = modified

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun setSdk(sdk: Sdk?) {
        TODO("Not yet implemented")
    }
}