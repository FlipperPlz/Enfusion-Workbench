package com.flipperplz.bisutils.languages.param.stubs.types

import com.flipperplz.bisutils.languages.param.ParamLanguage
import com.flipperplz.bisutils.languages.param.psi.ParamFile
import com.flipperplz.bisutils.languages.param.stubs.ParamFileStub
import com.flipperplz.bisutils.languages.param.stubs.impl.ParamFileStubImpl
import com.intellij.psi.PsiFile
import com.intellij.psi.StubBuilder
import com.intellij.psi.stubs.DefaultStubBuilder
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IStubFileElementType

class ParamFileElementType : IStubFileElementType<ParamFileStub>("PARAM_FILE", ParamLanguage) {
    companion object {
        val instance: ParamFileElementType = ParamFileElementType()
        private const val VERSION: Int = 0
    }

    override fun getBuilder(): StubBuilder = object : DefaultStubBuilder() {
        override fun createStubForFile(file: PsiFile): StubElement<*> = if(file is ParamFile) ParamFileStubImpl(file)
            else super.createStubForFile(file)
    }

    override fun getStubVersion(): Int = VERSION
}