package com.flipperplz.bisutils.pbo.entries

import com.flipperplz.bisutils.core.io.BisInputStream

enum class EntryMimeType(
    private val mime: Int
) {
    VERSION(0x56657273),
    COMPRESSED_DATA(0x43707273),
    ENCRYPTED_DATA(0x456e6372);

    //fun writeMime()

    companion object {
        fun readMime(reader: BisInputStream): EntryMimeType? = with(reader.readInt32()) {
            EntryMimeType.values().firstOrNull { it.mime == this }
        }
    }
}