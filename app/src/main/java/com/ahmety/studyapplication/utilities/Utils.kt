package com.ahmety.studyapplication.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): String {
    var formattedDate = ""
    val timeFormat = SimpleDateFormat("yyyy-MM-dd")
    timeFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date? = timeFormat.parse(this)
    val desiredFormat = SimpleDateFormat("dd/MM/yyyy")
    date?.let { dateTime ->
        formattedDate = desiredFormat.format(dateTime)
    }
    return formattedDate
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}