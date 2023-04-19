package com.flipperplz.enfusionWorkbench.vfs.pbo.vfs

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.impl.ArchiveHandler
import com.intellij.openapi.vfs.newvfs.ArchiveFileSystem
import com.intellij.openapi.vfs.newvfs.VfsImplUtil
import com.intellij.openapi.util.text.StringUtil
class PboFileSystem() : ArchiveFileSystem() {
    companion object {
        const val PROTOCOL = "pbo"
        const val PBO_SEPARATOR = "!/"

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

    override fun extractLocalPath(rootPath: String): String = StringUtil.trimEnd(rootPath, PBO_SEPARATOR)

    override fun extractRootPath(normalizedPath: String): String {
        val separatorIndex = normalizedPath.indexOf(PBO_SEPARATOR)
        assert(separatorIndex >= 0) { "Path passed to JarFileSystem must have jar separator '!/': $normalizedPath" }
        return normalizedPath.substring(0, separatorIndex + PBO_SEPARATOR.length)
    }

    override fun composeRootPath(localPath: String): String = localPath + PBO_SEPARATOR

    override fun getHandler(entryFile: VirtualFile): ArchiveHandler = VfsImplUtil.getHandler(this, entryFile) {
        PboHandler(it)
    }

}