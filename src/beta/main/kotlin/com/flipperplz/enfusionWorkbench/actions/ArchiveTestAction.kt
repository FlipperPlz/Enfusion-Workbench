package com.flipperplz.enfusionWorkbench.actions

import com.flipperplz.enfusionWorkbench.vfs.pbo.impl.PboFileSystem
import com.flipperplz.enfusionWorkbench.vfs.pbo.impl.PboHandler
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.newvfs.VfsImplUtil

class ArchiveTestAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {//"C:\Users\developer\Desktop\ai.pbo"
        testTriggered(LocalFileSystem.getInstance().findFileByPath("C:/Users/developer/Desktop/ai.pbo")!!)
    }

    private fun testTriggered(findFileByPath: VirtualFile) {

        val fs = PboFileSystem.instance
        val rootEntry = PboFileSystem.instance.getRootByLocal(findFileByPath)!!

        val configFile = rootEntry.children.first()
        val i = 1
        println("dd")
    }
}