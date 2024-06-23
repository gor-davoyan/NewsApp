package com.example.newsapp.data.mappers

import com.example.newsapp.data.entity.Fields
import com.example.newsapp.data.entity.NewsEntity
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.data.entity.NewsResponse

fun NewsItem.toNewsEntity(newsResponse: NewsResponse): NewsEntity {
    return NewsEntity(
        // need better solution, the api does not provide id for items
        id = newsResponse.startIndex++,
        title = webTitle,
        description = fields.bodyText,
        publicationDate = webPublicationDate,
        imageUrl = fields.thumbnail
    )
}

fun NewsEntity.toNews(): NewsItem {
    return NewsItem(
        index = id,
        webPublicationDate = publicationDate,
        webTitle = title,
        fields = Fields(description, imageUrl),
    )
}
