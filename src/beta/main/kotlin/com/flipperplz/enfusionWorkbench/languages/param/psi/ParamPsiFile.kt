package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.enfusionWorkbench.languages.param.ParamFileType
import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamClass
import com.flipperplz.enfusionWorkbench.languages.param.psi.ext.ParamStatement
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.util.PathUtil

class ParamPsiFile(
    viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, ParamLanguage) {

    val isRvmat: Boolean = name.endsWith(".rvmat")
    val isModConfig: Boolean = name == "config.cpp" || name == "config.bin"
    val isWorkshopConfig: Boolean =  name == "mod.cpp" || name == "mod.bin"
    val paramName: String = name.substring(name.length - (PathUtil.getFileExtension(name)?.length ?: 0) )

    val myStatements: CachedValue<List<ParamStatement>> = CachedValuesManager.getManager(project).createCachedValue({
        CachedValueProvider.Result.create(children.filterIsInstance<ParamStatement>(), this)
    }, false)

    val myClasses: CachedValue<List<ParamClass>> = CachedValuesManager.getManager(project).createCachedValue({
        CachedValueProvider.Result.create(myStatements.value.filterIsInstance<ParamClass>(), this)
    }, false)


    override fun getFileType(): FileType = ParamFileType

    override fun toString(): String = "Param File"



}