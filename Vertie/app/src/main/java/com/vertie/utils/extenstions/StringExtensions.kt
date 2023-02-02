package com.vertie.utils.extensions

import com.google.common.base.CaseFormat
import java.util.*
import kotlin.collections.ArrayList

fun String?.toCamelCase(): String? {
    return if (this != null) {
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, this)
    } else {
        null
    }

}


fun String?.getInitials(): String? {
    return if (!this.isNullOrEmpty()) {
        val split = ArrayList(this.split(" ")
            .map { char ->
                if (char.isNotEmpty()) char.uppercase().first() else null
            }
        )

        return split.filterNotNull().take(2).joinToString("")
    } else {
        null
    }

}