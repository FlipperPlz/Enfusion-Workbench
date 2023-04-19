package com.flipperplz.enfusionWorkbench.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.impl.ZipHandler
import com.intellij.openapi.vfs.newvfs.VfsImplUtil

class ArchiveTestAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        testTriggered(LocalFileSystem.getInstance().findFileByPath("C:/Users/developer/Downloads/Bytecode-Viewer-2.11.2.jar!/")!!)
    }

    private fun testTriggered(findFileByPath: VirtualFile) {
        val jarFileSystem = JarFileSystem.getInstance()
        val root = VfsImplUtil.getHandler<ZipHandler>(JarFileSystem.getInstance(), findFileByPath) { ZipHandler(it) }
    }
}