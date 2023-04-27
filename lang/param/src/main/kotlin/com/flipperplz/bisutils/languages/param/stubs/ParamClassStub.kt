package com.flipperplz.bisutils.languages.param.stubs

import com.flipperplz.bisutils.languages.param.psi.ParamPsiExternalClass
import com.intellij.psi.stubs.NamedStub

interface ParamClassStub : NamedStub<ParamPsiExternalClass> {
    val outerParent: String
    val className: String
    val superName: String?
}