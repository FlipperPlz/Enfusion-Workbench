package com.flipperplz.enfusionWorkbench.sdk.pbo

import com.flipperplz.enfusionWorkbench.vfs.pbo.PboFileType
import com.intellij.openapi.vfs.VirtualFile

/**
 * @return List of PBO Files Found inside the PBO (pbo, signature)
 */
fun VirtualFile.asGameAddon(): List<VirtualFile> {
    if(!isDirectory) return emptyList()
    val addons = mutableListOf<VirtualFile>()

    children.firstOrNull {it.isDirectory && it.name.equals("common", true)}?.let { commonFolder ->
        addons.addAll(commonFolder.children.filter { it.fileType is PboFileType })
    }

    children.firstOrNull {it.isDirectory && it.name.equals("addons", true)}?.let { addonsFolder ->
        addons.addAll(addonsFolder.children.filter { it.fileType is PboFileType })
    }

    children.firstOrNull {it.isDirectory && it.name.equals("Campaigns", true)}?.let { campaignsFolder ->
        addons.addAll(campaignsFolder.children.filter { it.fileType is PboFileType })
    }

    return addons
}