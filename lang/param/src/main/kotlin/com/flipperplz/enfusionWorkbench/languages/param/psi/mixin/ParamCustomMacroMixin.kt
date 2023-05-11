package com.flipperplz.enfusionWorkbench.languages.param.psi.mixin

import com.flipperplz.bisutils.param.node.RapElement
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamCustomMacro
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamLiteral
import com.flipperplz.enfusionWorkbench.languages.param.psi.impl.ParamPureDirectiveImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.util.parentOfType

//TODO(Ryann): Macro Evaluation Add (RapMacro::Evaluate -> RapElement )
abstract class ParamCustomMacroMixin(node: ASTNode) : ParamPureDirectiveImpl(node), ParamCustomMacro {
    override val slimParent: RapElement?
        get() = parent as? RapElement
    override val slimMacroName: String?
        get() = TODO() //TODO(Ryann): external rule not pulled into psi
    override val slimMacroArguments: List<String>
        get() = macroArgumentList.map { it.text.replace("\\\n", "\n") }
    override val slimIsCommand: Boolean
        get() = parentOfType<ParamLiteral>() == null
}