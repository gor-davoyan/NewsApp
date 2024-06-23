package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.data.entity.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

    fun getNews(): Flow<PagingData<NewsItem>>

    fun searchNews(query: String): Flow<PagingData<NewsItem>>
}