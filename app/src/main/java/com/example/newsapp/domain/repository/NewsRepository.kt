package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.data.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<PagingData<NewsEntity>>

    fun searchNews(query: String): Flow<PagingData<NewsEntity>>
}