package com.flipperplz.enfusionWorkbench.languages.pbo.vfs

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.impl.ArchiveHandler
import com.intellij.openapi.vfs.newvfs.ArchiveFileSystem
import com.intellij.openapi.vfs.newvfs.VfsImplUtil

class PboFileSystem() : ArchiveFileSystem() {
    companion object {
        private const val PROTOCOL = "pbo"
        val instance: PboFileSystem
            get() = VirtualFileManager.getInstance().getFileSystem(PROTOCOL) as PboFileSystem
    }

    override fun isCorrectFileType(local: VirtualFile): Boolean = with(local.extension) {
        this == "pbo" || this == "ebo"
    }
    override fun getProtocol(): String = PROTOCOL
    override fun findFileByPath(path: String): VirtualFile? = VfsImplUtil.findFileByPath(this, path)

    override fun refresh(asynchronous: Boolean) = VfsImplUtil.refresh(this, asynchronous)

    override fun refreshAndFindFileByPath(path: String): VirtualFile? = VfsImplUtil.refreshAndFindFileByPath(this, path)

    override fun findFileByPathIfCached(path: String): VirtualFile? = VfsImplUtil.findFileByPathIfCached(this, path)
    override fun extractLocalPath(rootPath: String): String {
        TODO("Not yet implemented")
    }
    override fun extractRootPath(normalizedPath: String): String {
        TODO("Not yet implemented")
    }

    override fun composeRootPath(localPath: String): String {
        TODO("Not yet implemented")
    }

    override fun getHandler(entryFile: VirtualFile): ArchiveHandler = VfsImplUtil.getHandler(this, entryFile) {
        PboHandler(it)
    }

}