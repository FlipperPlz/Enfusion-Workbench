package com.flipperplz.enfusionWorkbench.sdk.dayz

import com.flipperplz.enfusionWorkbench.vfs.pbo.PboFileType
import com.intellij.openapi.projectRoots.*
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.Element
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class DayZSdkType : SdkType("DayZ") {
    override fun adjustSelectedSdkHome(previousHome: String): String {
        //TODO(ryann): look for alternative sdk home
        return super.adjustSelectedSdkHome(previousHome) //TODO:CALLED WHEN suggestHomePath is wrong
    }

    override fun getPresentableName(): String = "DayZ Base-Game"

    override fun suggestHomePath(): String =
        Path.of(System.getenv("ProgramFiles(X86)"), "Steam", "steamapps", "common", "DayZ").absolutePathString()

    override fun isValidSdkHome(path: String): Boolean = findFrameworkFolders(path).containsKey("dta")

    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String = currentSdkName ?: "DayZ"

    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        TODO("Not yet implemented")
    }

    override fun setupSdkPaths(sdk: Sdk, sdkModel: SdkModel): Boolean {
        val sdkHome = sdk.homeDirectory ?: return false
        val modificator = sdk.sdkModificator

        modificator.sdkAdditionalData = DayZSdkAdditionalData(
            sdkHome,
            findFrameworkFolders(sdkHome.path)
        )

        val additionalData = modificator.sdkAdditionalData as? DayZSdkAdditionalData ?: return false
        modificator.addRoot(additionalData.gameHome ?: return false, OrderRootType.CLASSES)
        additionalData.dlcHomes.forEach { (_, dlcHome) ->
            modificator.addRoot(dlcHome, OrderRootType.CLASSES)
        }

        modificator.commitChanges()

        return super.setupSdkPaths(sdk, sdkModel)
    }

    override fun createAdditionalDataConfigurable(
        sdkModel: SdkModel,
        sdkModificator: SdkModificator
    ): AdditionalDataConfigurable = DayZSdkAdditionalDataConfigurable()

    private fun findFrameworkFolders(homePath: String): Map<String, VirtualFile> {
        val ret = mutableMapOf<String, VirtualFile>()
        val sdkHome = LocalFileSystem.getInstance().findFileByPath(homePath) ?: return ret
        val validateHome: (VirtualFile, String) -> Unit = { home, dlcName ->
            if (home.children.any { it.fileType is PboFileType })
                ret[dlcName] = home
        }
        val locateDLC: (String) -> Unit = { dlcName ->
            sdkHome.findChild(dlcName)?.findChild("Addons")?.let {
                validateHome(it, dlcName)
            }
        }

        sdkHome.findChild("dta")?.let { validateHome(it, "dta") }
        locateDLC("bliss")

        return ret
    }
}