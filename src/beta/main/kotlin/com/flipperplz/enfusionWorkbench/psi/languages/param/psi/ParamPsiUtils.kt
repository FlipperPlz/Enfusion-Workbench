package com.flipperplz.enfusionWorkbench.psi.languages.param.psi

import com.flipperplz.enfusionWorkbench.psi.languages.param.psi.interfaces.ParamPsiStatementScope
import com.intellij.psi.PsiNamedElement
import io.ktor.util.reflect.*
import kotlin.reflect.full.isSuperclassOf


//STATEMENT LIST
fun List<ParamStatement>.getClasses(): List<ParamClassStatement> =
    filterIsInstance<ParamClassStatement>()

fun List<ParamStatement>.getExternalClasses(): List<ParamClassStatement> =
    getClasses().filter { it.isExternal }

inline fun <reified T: ParamStatement> List<ParamStatement>.filterStatements(): List<T> =
    filterIsInstance<T>()

inline fun <reified T: ParamStatement> List<ParamStatement>.getStatementNamed(statements: List<ParamStatement>, name: String): List<T> =
    filterStatements<T>().filter { it is PsiNamedElement && it.name == name }

inline fun <reified T: ParamStatement> ParamPsiStatementScope.getStatementNamed(name: String) =
    statements.getStatementNamed<T>(statements, name)
inline fun <reified T: ParamStatement> ParamPsiStatementScope.filterStatements(): List<T> = statements.filterStatements();

fun ParamPsiStatementScope.firstStatement(): ParamStatement? = statements.firstOrNull()
inline fun <reified T: ParamStatement> ParamPsiStatementScope.firstStatementOfType(): T? = statements.filterIsInstance<T>().firstOrNull()
fun ParamNumericLiteral.toNumber(): Number? = text.toIntOrNull() ?: text.toFloatOrNull() ?: text.toDoubleOrNull()
inline fun <reified T: ParamStatement> ParamPsiStatementScope.locateStatementInScope( name: String): T? {
    var i: ParamPsiStatementScope? = this
    var found: T? = null

    while(found == null && i != null) {
        found = i.getStatementNamed<T>(name).firstOrNull()
        i = i.previousScope
    }

    return found
}

fun ParamStringLiteral.isProceduralTexture(): Boolean = stringContent is ParamProceduralTexture

fun ParamStringLiteral.asProceduralTexture(): ParamProceduralTexture? = this.stringContent as? ParamProceduralTexture