package com.flipperplz.enfusionWorkbench.languages.param.parser

import com.flipperplz.enfusionWorkbench.languages.param.ParamLanguage
import com.flipperplz.enfusionWorkbench.languages.param.lexer.ParamLexerAdapter
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamPsiFile
import com.flipperplz.enfusionWorkbench.languages.param.psi.required.ParamTokenSets
import com.flipperplz.enfusionWorkbench.languages.param.psi.ParamTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class ParamParserDefinition : ParserDefinition {
    companion object {
        val FILE: IFileElementType = IFileElementType(ParamLanguage)
    }

    override fun createLexer(project: Project?): Lexer = ParamLexerAdapter()

    override fun createParser(project: Project?): PsiParser = ParamParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet = ParamTokenSets.STRINGS

    override fun createElement(node: ASTNode?): PsiElement = ParamTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = ParamPsiFile(viewProvider)
}