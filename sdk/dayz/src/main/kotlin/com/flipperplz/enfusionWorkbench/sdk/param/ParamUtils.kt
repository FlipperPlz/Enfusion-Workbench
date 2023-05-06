package com.flipperplz.enfusionWorkbench.sdk.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiNameIdentifierOwner
import kotlinx.coroutines.repackaged.net.bytebuddy.implementation.bind.MethodDelegationBinder.MethodInvoker.Virtual

fun ParamPsiCommandsHolder.getClass(name: String): ParamPsiClass? = commands.filterIsInstance<ParamPsiClass>().firstOrNull {
    it.name == name
}

fun ParamPsiCommandsHolder.getEntry(name: String): ParamParameterStatement? = commands.filterIsInstance<ParamParameterStatement>().firstOrNull {
    it.name == name
}

inline fun <reified T: PsiNameIdentifierOwner> ParamPsiCommandsHolder.getEntry(name: String): T? = commands.filterIsInstance<T>().firstOrNull {
    it.name == name
}


fun VirtualFile.findConfigDirectories(): List<VirtualFile> {
    if(!isDirectory) return emptyList()
    fun scanDir(file: VirtualFile): List<VirtualFile> {
        val found = mutableListOf<VirtualFile>()
        for(child in file.children) {
            when {
                child.isDirectory -> found.addAll(scanDir(child))
                else -> {
                    if((child.name == "config.cpp" || child.name == "config.bin") && !found.contains(child.parent))
                        found.add(child.parent)
                }
            }
        }

        return found
    }

    return scanDir(this).sortedWith(
        compareBy( {
            it.name.replace("\\", "")
        }, {
            it.name
        })
    )
}

fun VirtualFile.findConfig(): ParamPsiFile? {
    if(!isDirectory) return null
    return (children.firstOrNull() { it.name == "config.cpp" } ?:
    children.firstOrNull() { it.name == "config.bin" }) as ParamPsiFile }

