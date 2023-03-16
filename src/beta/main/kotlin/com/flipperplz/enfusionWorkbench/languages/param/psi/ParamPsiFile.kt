package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatement
import com.flipperplz.enfusionWorkbench.languages.param.psi.contexts.ParamClassContext
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.util.PathUtil

class ParamPsiFile(
    viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, ParamLanguage), ParamClassContext {
    val isRvmat: Boolean = name.endsWith(".rvmat")
    val isModConfig: Boolean = name == "config.cpp" || name == "config.bin"
    val isWorkshopConfig: Boolean =  name == "mod.cpp" || name == "mod.bin"
    private val paramName: String = name.substring(name.length - (PathUtil.getFileExtension(name)?.length ?: 0) )

    override val tokenType: IElementType = node.elementType
    override val previousScope: ParamClassContext? = null;
    override val className: ParamIdentifier = ParamElementGenerator.createIdentifier(project, paramName)
    override val superClass: ParamClassContext? = null
    override val isExternal: Boolean = false
    override val childStatements: List<ParamStatement> = children.filterIsInstance<ParamStatement>()

    override fun getFileType(): FileType = ParamFileType

    override fun toString(): String = "Param File"
}