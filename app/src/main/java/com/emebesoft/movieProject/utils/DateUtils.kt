package com.emebesoft.movieProject.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateUtils {

    companion object{
        fun dateConverter(date: String): String {
            val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            val convertedDate = inputDate.parse(date)
            return outputDate.format(convertedDate)
        }
    }
}