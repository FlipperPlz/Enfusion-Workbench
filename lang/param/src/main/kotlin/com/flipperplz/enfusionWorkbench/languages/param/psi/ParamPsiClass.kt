package com.flipperplz.enfusionWorkbench.languages.param.psi

import com.flipperplz.bisutils.param.slim.ParamSlimClass
import com.flipperplz.bisutils.param.slim.ParamSlimCommand

interface ParamPsiClass : ParamPsiExternalClass, ParamPsiCommandsHolder, ParamSlimClass {
    val paramSuperClass: ParamIdentifier?
    override var superClass: String?
        get() = paramSuperClass?.name
        set(value) { value?.let { paramSuperClass?.setName(it) } }

    override var slimCommands: List<ParamSlimCommand>
        get() = commands
        set(value) = throw NotImplementedError()

    override fun toEnforce(): String {
        return super<ParamSlimClass>.toEnforce()
    }


}