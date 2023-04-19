package com.flipperplz.enfusionWorkbench.sdk.dayz

import com.flipperplz.enfusionWorkbench.sdk.EnfusionSDKType
import com.flipperplz.enfusionWorkbench.vfs.pbo.PboFileType
import com.intellij.openapi.projectRoots.*
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.vcs.log.graph.utils.walk
import org.jdom.Element
import java.nio.file.Path
import kotlin.io.path.absolutePathString

class DayZSdkType : EnfusionSDKType(
    gameName = "DayZ",
    steamGameId = 221100,
    steamServerId = 223350,
    steamToolsId = 830640
) {
    override fun getPresentableName(): String = "DayZ"

    override fun suggestHomePath(): String = Path.of(System.getenv("ProgramFiles(X86)"), "Steam", "steamapps", "common", "DayZ").absolutePathString()

    override fun isValidSdkHome(path: String): Boolean = findFrameworkFolders(path).containsKey("dta")

    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String = currentSdkName ?: "DayZ"

    override fun setupSdkPaths(sdk: Sdk, sdkModel: SdkModel): Boolean {
        return super.setupSdkPaths(sdk, sdkModel)
    }

    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        TODO("Not yet implemented")
    }

    override fun createAdditionalDataConfigurable(
        sdkModel: SdkModel,
        sdkModificator: SdkModificator
    ): AdditionalDataConfigurable? = null

    private fun findFrameworkFolders(homePath: String) : Map<String, VirtualFile> {
        val ret = mutableMapOf<String, VirtualFile>()
        val sdkHome = LocalFileSystem.getInstance().findFileByPath(homePath) ?: return ret
        val validateHome: (VirtualFile, String) -> Unit = {home, dlcName ->
            if(home.children.any { it.fileType is PboFileType })
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