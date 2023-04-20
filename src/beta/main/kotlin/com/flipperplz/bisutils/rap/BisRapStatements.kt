package com.flipperplz.bisutils.rap


class BisRapDeleteStatement(
    val target: String
) : BisRapStatement(4) {
    override fun writeText(): String = "delete $target;"
    override fun writeBinary(): ByteArray = super.writeBinary().plus(target.toByteArray(Charsets.UTF_8)).plus(0)
}

class BisRapClassStatement(
    val className: String,
    val superclass: String? = null,
    val external: Boolean = false,
) : BisRapStatement(null) {

    override val mime: Byte
        get() = if(external) 3 else 0

    override fun writeText(): String = "class $className" + if(external) ';' else {
        if(superclass != null) " : $superclass" else " {\n" +
                //TODO STATEMENTS
            "};"
    }
    override fun writeBinary(): ByteArray {
        //TODO
        TODO()
    }
}