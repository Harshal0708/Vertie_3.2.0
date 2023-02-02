package com.vertie.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object CustomDateUtils {
    fun formatDate(dateString: String, fromFormat: String, toFormat: String): String? {
        val fromDateFormatter = DateTimeFormatter.ofPattern(fromFormat)
        val date = LocalDateTime.parse(dateString, fromDateFormatter)
        val toDateFormatter = DateTimeFormatter.ofPattern(toFormat)
        return date?.format(toDateFormatter)
    }
}