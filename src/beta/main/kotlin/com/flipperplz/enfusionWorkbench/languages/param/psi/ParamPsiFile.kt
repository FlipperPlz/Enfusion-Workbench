package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ast.ParamStatementHolder
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamIdentifier
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement
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
) : PsiFileBase(viewProvider, ParamLanguage), ParamStatementHolder {

    val myStatements: CachedValue<List<ParamStatement>> = CachedValuesManager.getManager(project).createCachedValue({
        CachedValueProvider.Result.create(children.filterIsInstance<ParamStatement>(), this)
    }, false)

    val myClasses: CachedValue<List<ParamClass>> = CachedValuesManager.getManager(project).createCachedValue({
        CachedValueProvider.Result.create(myStatements.value.filterIsInstance<ParamClass>(), this)
    }, false)

    val isRvmat: Boolean = name.endsWith(".rvmat")
    val isModConfig: Boolean = name == "config.cpp" || name == "config.bin"
    val isWorkshopConfig: Boolean =  name == "mod.cpp" || name == "mod.bin"
    val paramName: String = name.substring(name.length - (PathUtil.getFileExtension(name)?.length ?: 0) )

    override val previousScope: ParamStatementHolder? = null;
    override val childScopes: List<ParamStatementHolder> = myClasses.value
    override val statements: List<ParamStatement> = myStatements.value
    override val paramComponentName: ParamIdentifier = ParamElementGenerator.createIdentifier(project, paramName)
    override val tokenType: IElementType = node.elementType
    override fun getNameIdentifier(): PsiElement = paramComponentName

    override fun getFileType(): FileType = ParamFileType

    override fun toString(): String = "Param File"
}