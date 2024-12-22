package com.ahmety.studyapplication.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.ahmety.studyapplication.R
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@SuppressLint("SimpleDateFormat")
fun String.toFormatDate(): String {
    var formattedDate = ""
    val timeFormat = SimpleDateFormat("yyyy-MM-dd")
    timeFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date: Date? = timeFormat.parse(this)
    val desiredFormat = SimpleDateFormat("dd MMM EEEE")
    date?.let { dateTime ->
        formattedDate = desiredFormat.format(dateTime)
    }
    return formattedDate
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

@SuppressLint("ResourceType")
fun ImageView.loadImages(url: String) {

    Glide.with(this)
        .load(url)
        .placeholder(R.raw.image_loading)
        .error(R.drawable.ic_error_img)
        .into(this)

}

val forbiddenCharacters = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '=', '+')

val customFilter = InputFilter { source, _, _, _, _, _ ->
    if (source.any { it.isDigit() || forbiddenCharacters.contains(it) }) {
        ""
    } else {
        source
    }
}