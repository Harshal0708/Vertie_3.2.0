package com.vertie.utils.bindingUtils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DataFormate {

    fun DataFormateInDDMMYYYY(date1:String):String{
        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = LocalDate.parse(date1, secondApiFormat)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
        val inputDateStr = date.toString()
        val date2: Date = inputFormat.parse(inputDateStr)
        val outputDateStr: String = outputFormat.format(date2)
        return outputDateStr
    }

}