package com.esum.common.date

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


//yyyyMMdd
    fun getCurrentDate(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        }else {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            "$year$month$day"
        }
    }


    fun addDays(day : Int):String {
        return if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            val currentDate = LocalDate.now()
            val newDate = currentDate.plusDays(day.toLong())
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            newDate.format(formatter)
        }else {
            val calendar = Calendar.getInstance()
            val daysToAdd = 7
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)
            val formatter = SimpleDateFormat("yyyyMMdd" , Locale.getDefault())
            formatter.format(calendar.time)
        }

    }

