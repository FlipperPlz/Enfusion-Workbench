package com.flipperplz.enfusionWorkbench.languages.param

import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory

private fun createDummyFile(project: Project, content: String): ParamPsiFile = PsiFileFactory.getInstance(project)
    .createFileFromText("__dummy-script__.cpp", ParamFileType.instance, content) as ParamPsiFile
