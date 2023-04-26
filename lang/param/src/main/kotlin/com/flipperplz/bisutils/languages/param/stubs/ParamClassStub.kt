package com.flipperplz.bisutils.languages.param.stubs

import com.flipperplz.bisutils.languages.param.psi.ParamPsiClassBase
import com.intellij.psi.stubs.NamedStub

interface ParamClassStub : NamedStub<ParamPsiClassBase> {
    val outerParent: String
    val className: String
    val superName: String?
}