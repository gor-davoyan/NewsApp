package com.example.newsapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val publicationDate: String,
    val imageUrl: String?
)
