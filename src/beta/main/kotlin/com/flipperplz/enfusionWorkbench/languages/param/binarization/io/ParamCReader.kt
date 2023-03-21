package com.flipperplz.enfusionWorkbench.languages.param.binarization.io

import com.flipperplz.bisutils.core.io.BisInputStream
import com.flipperplz.enfusionWorkbench.languages.param.ParamElementFactory
import com.flipperplz.enfusionWorkbench.languages.param.enumerations.ParamAssignmentOperation
import com.flipperplz.enfusionWorkbench.languages.param.psi.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.ByteArrayInputStream

class ParamCReader private constructor(
    private val project: Project,
    private val reader: BisInputStream
) {
    data class ParamFileDataHolder(
        val statements: List<ParamStatement> = emptyList(),
        val error: String? = null
    )

    data class ParamElementLocationInfo(
        val offsetLocation: Long,
        val offset: Long
    )

    private var dataHolder: ParamFileDataHolder? = null

    fun readFile(): List<ParamStatement> {
        dataHolder?.let { return it.statements }
        reader.reset()
        if(!containsParamHeader()) {
            dataHolder = ParamFileDataHolder(emptyList(), "Failed to find Param Magic!")
            return emptyList()
        }
        val enumOffset = reader.readInt32()
        reader.read()
        val statements = mutableMapOf<ParamStatement, ParamElementLocationInfo>()

        repeat(reader.readCompactInteger()) {
            with (readStatement()) {
                statements[first] = second
            }
        }

        var sortedStatements = statements.filterKeys {it is ParamClass && !it.isExternalClass}
        sortedStatements = sortedStatements.toSortedMap(compareBy { sortedStatements[it]?.offset })

        for ((clazzStatement, offsetInfo) in sortedStatements) {
            val clazz = clazzStatement as ParamClass
            reader.skip(offsetInfo.offset - reader.byteCount)
            assert(reader.byteCount == offsetInfo.offset) {"Failed to seek to offset (expected: ${offsetInfo.offset}; instead got ${reader.byteCount}"}

            loadChildClass(clazz, offsetInfo)
        }

        return statements.keys.toList()
    }

    private fun loadChildClass(clazz: ParamClass, offsetInfo: ParamCReader.ParamElementLocationInfo?) {
        val superClass = reader.readAsciiZ()
        if(superClass == "") clazz.removeSuperClass()

        repeat(reader.readCompactInteger()) {
            clazz append readStatement().first
            loadChildClass(clazz, offsetInfo)
        }
    }

    private fun readStatement(): Pair<ParamStatement, ParamElementLocationInfo> = reader.byteCount.let { byteCount ->
        when(reader.read()) {
            0 -> Pair(
                ParamElementFactory.createClass(project, reader.readAsciiZ(Charsets.UTF_8), "dummySuper", mutableListOf()),
                ParamElementLocationInfo(reader.byteCount, reader.readInt32().toLong())
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

    private fun containsParamHeader(): Boolean = with(reader.readNBytes(4)) {
        this[0] == 0.toByte() &&
        this[1] == 114.toByte()
        this[2] == 97.toByte() &&
        this[3] == 80.toByte() &&
        reader.readInt32() == 0 &&
        reader.readInt32() == 8
    }

    companion object {

        fun readFile(project: Project, virtualFile: VirtualFile): ParamFileDataHolder {

            // Check for valid SFC
            if (isParamC(virtualFile)) {
                throw Exception("Cannot parse non-rapified file as ParamC")
            }

            // Parse or create data holder
            return try {
                readRaw(project, virtualFile)
            } catch (e: Exception) {
                throw e
            }
        }
        private fun readRaw(project: Project, virtualFile: VirtualFile): ParamFileDataHolder =
            ParamFileDataHolder(ParamCReader(project, BisInputStream(ByteArrayInputStream(virtualFile.contentsToByteArray()))).readFile())



        fun isParamC(virtualFile: VirtualFile): Boolean = virtualFile.contentsToByteArray().let out@{ bytes ->
            bytes[0] == 0.toByte() &&
                    bytes[1] == 114.toByte()
            bytes[2] == 97.toByte() &&
                    bytes[3] == 80.toByte() &&
                    bytes[4] == 0.toByte() &&
                    bytes[5] == 0.toByte() &&
                    bytes[6] == 0.toByte() &&
                    bytes[7] == 0.toByte() &&
                    bytes[8] == 8.toByte() &&
                    bytes[9] == 0.toByte() &&
                    bytes[10] == 0.toByte() &&
                    bytes[11] == 0.toByte();

        } && (virtualFile.extension == "cpp" || virtualFile.extension == "rvmat" || virtualFile.extension == "bin")
    }
}