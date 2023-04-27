package com.flipperplz.bisutils.languages.param.stubs.impl

import com.flipperplz.bisutils.languages.param.psi.ParamPsiFile
import com.flipperplz.bisutils.languages.param.stubs.ParamFileStub
import com.flipperplz.bisutils.languages.param.stubs.types.ParamFileElementType
import com.intellij.psi.stubs.PsiFileStubImpl

class ParamFileStubImpl(file: ParamPsiFile) : PsiFileStubImpl<ParamPsiFile>(file), ParamFileStub {
    override fun getType(): ParamFileElementType = ParamFileElementType.instance
}