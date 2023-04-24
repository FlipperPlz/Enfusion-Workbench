package com.flipperplz.enfusionWorkbench.psi.languages.param.psi.elements

import com.flipperplz.enfusionWorkbench.psi.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.psi.languages.param.stubs.ParamFileStub
import com.intellij.psi.tree.IStubFileElementType

class ParamElementType(debugName: String) : IStubFileElementType<ParamFileStub>(debugName, ParamLanguage)