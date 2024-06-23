package com.example.newsapp.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

const val ITEMS_PER_PAGE = 20

const val TOP_BAR_TEXT_NEWS = "News"

fun String.toReadableDate(): String {
    val zonedDateTime = ZonedDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
    return zonedDateTime.format(formatter)
}