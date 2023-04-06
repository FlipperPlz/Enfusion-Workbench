package com.flipperplz.enfusionWorkbench.languages.param.psi.mixins

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamStringLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPsiElementImpl
import com.flipperplz.enfusionWorkbench.languages.param.utils.ParamStringType
import com.flipperplz.enfusionWorkbench.utils.BisErasableLazy
import com.flipperplz.enfusionWorkbench.utils.erasableLazy
import com.intellij.lang.ASTNode
import kotlin.properties.Delegates

abstract class ParamStringMixin(node: ASTNode) : ParamPsiElementImpl(node), ParamStringLiteral{
    private val lazyStringType: BisErasableLazy<ParamStringType> = erasableLazy {
        ParamStringType.detectStringType(this)
    }

    private val lazyStringContents: BisErasableLazy<String> = erasableLazy {
        stringType.escapedString(this)
    };
    val stringType: ParamStringType get() = lazyStringType.value
    val stringContents: String get() = lazyStringContents.value

    override fun subtreeChanged() {
        if(children.any { it == (stringType.startToken ?: ParamTypes.STRING_DOUBLE_START) } ||
           children.any { it == (stringType.endToken ?: ParamTypes.STRING_DOUBLE_END) } ) {
            lazyStringType.reset()
        }
        lazyStringContents.reset()
        super.subtreeChanged()
    }
}