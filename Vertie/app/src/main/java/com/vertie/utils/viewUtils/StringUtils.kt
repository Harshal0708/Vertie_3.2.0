package com.vertie.utils.viewUtils

fun String.capitalizeFirstLetter() = this.split(" ").joinToString(" ") { it.uppercase() }.trimEnd()