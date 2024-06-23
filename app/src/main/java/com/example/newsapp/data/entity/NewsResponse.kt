package com.example.newsapp.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseWrapper(
    @SerialName("response")
    val response: NewsResponse
)

@Serializable
data class NewsResponse(
    @SerialName("startIndex")
    var startIndex: Int,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("pageSize")
    val pageSize: Int,
    @SerialName("results")
    val results: List<NewsItem>
)

@Parcelize
@Serializable
data class NewsItem(
    val index: Int?,
    @SerialName("webPublicationDate")
    val webPublicationDate: String,
    @SerialName("webTitle")
    val webTitle: String,
    @SerialName("fields")
    val fields: Fields,
) : Parcelable

@Parcelize
@Serializable
data class Fields(
    @SerialName("bodyText")
    val bodyText: String,
    @SerialName("thumbnail")
    val thumbnail: String?
) : Parcelable
