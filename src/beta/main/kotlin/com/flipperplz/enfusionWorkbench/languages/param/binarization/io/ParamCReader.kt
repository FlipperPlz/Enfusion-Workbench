package com.flipperplz.enfusionWorkbench.languages.param.binarization.io

import com.flipperplz.bisutils.core.io.BisInputStream
import com.flipperplz.enfusionWorkbench.languages.param.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamAssignmentOperation
import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.openapi.project.Project
import org.apache.batik.dom.ExtensibleDOMImplementation.ElementFactory

class ParamCReader(
    private val project: Project,
    private val reader: BisInputStream
) {
    data class ParamFileDataHolder(
        val paramFile: ParamFile? = null,
        val error: String? = null
    )

    data class ParamElementLocationInfo(
        val offsetLocation: Long,
        val offset: Int
    )

    private var dataHolder: ParamFileDataHolder? = null


    suspend fun readFile(): ParamFile? {
        dataHolder?.let { return it.paramFile }
        reader.reset()
        if(!containsParamHeader()) {
            dataHolder = ParamFileDataHolder(null, "Failed to find Param Magic!")
            return null
        }
        val enumOffset = reader.readInt32()
        reader.read()
        val statements = mutableMapOf<ParamStatement, ParamElementLocationInfo>()

        repeat(reader.readCompactInteger()) {
            with (readStatement()) {
                statements[first] = second
            }
        }

        for (clazz in statements.keys.filterIsInstance<ParamClass>().filter { !it.isExternalClass }) {
            //TODO:
        }

        TODO()
    }


    private fun readStatement(): Pair<ParamStatement, ParamElementLocationInfo> = reader.byteCount.let { byteCount ->
        when(reader.read()) {
            0 -> Pair(
                ParamElementFactory.createClass(project, reader.readAsciiZ(Charsets.UTF_8), "dummySuper", mutableListOf()),
                ParamElementLocationInfo(reader.byteCount, reader.readInt32())
            )
            1 -> Pair(
                ParamElementFactory.createAssignment(project, reader.readAsciiZ(Charsets.UTF_8), readParamValue()!!),
                ParamElementLocationInfo(byteCount,0)
            )
            2 -> Pair(
                ParamElementFactory.createArrayAssignment(project, reader.readAsciiZ(Charsets.UTF_8), readParamArray()),
                ParamElementLocationInfo(byteCount,0)
            )
            3 -> Pair(
                ParamElementFactory.createExternalClass(project, reader.readAsciiZ(Charsets.UTF_8),),
                ParamElementLocationInfo(byteCount,0)
            )
            4 -> Pair(
                ParamElementFactory.createDelete(project, reader.readAsciiZ(Charsets.UTF_8)),
                ParamElementLocationInfo(byteCount,0)
            )
            5 -> with(when(reader.readInt32()) {
                0 -> ParamAssignmentOperation.SUB_ASSIGN
                1 -> ParamAssignmentOperation.ADD_ASSIGN
                else -> throw Exception("Unknown operation")
            }) {
                Pair(
                    ParamElementFactory.createArrayAssignment(project, reader.readAsciiZ(Charsets.UTF_8), readParamArray(),this),
                    ParamElementLocationInfo(byteCount,0)
                )
            }
            else -> throw Exception("Unknown ParamStatement")
        }
    }

    private fun readParamValue(): ParamLiteral? = when(reader.read()) {
        0 -> ParamElementFactory.createString(project, "\"${reader.readAsciiZ().replace("\"", "\"\"")}\"")
        1 -> ParamElementFactory.createNumber(project, reader.readFloat())
        2 -> ParamElementFactory.createNumber(project, reader.readInt32())
        else -> null
    }

    private fun readParamArrayElement(): ParamArrayElement {
        readParamValue()?.let { return it }
        //assume child array
        return readParamArray()
    }

    private fun readParamArray(): ParamArray = ParamElementFactory.createArray(project, List(reader.readCompactInteger()) { readParamArrayElement() })

    private fun containsParamHeader(): Boolean = with(reader.readBytes(4)) {
        this[0].toChar() == '\u0000' &&
        this[1].toChar() == 'r' &&
        this[2].toChar() == 'a' &&
        this[3].toChar() == 'P' &&
        reader.readInt32() == 0 &&
        reader.readInt32() == 8
    }
}