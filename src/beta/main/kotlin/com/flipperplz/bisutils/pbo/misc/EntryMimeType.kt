package com.flipperplz.bisutils.pbo.misc

enum class EntryMimeType(
    val mime: Int
) {
    VERSION(0x56657273),
    NORMAL_DATA(0x43707273),
    ENCRYPTED_DATA(0x456e6372),
    DUMMY(0x00000000);

    companion object {

        fun fromMime(mime: Int): EntryMimeType? {
            return values().firstOrNull { it.mime == mime }
        }
    }


}