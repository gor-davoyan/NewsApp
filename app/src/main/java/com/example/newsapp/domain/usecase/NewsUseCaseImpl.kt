package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.data.mappers.toNews
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsUseCase {

    override fun getNews(): Flow<PagingData<NewsItem>> {
        return newsRepository.getNews()
            .map { pagingData ->
                pagingData.map { it.toNews() }
            }
    }

    override fun searchNews(query: String): Flow<PagingData<NewsItem>> {
        return newsRepository.searchNews(query)
            .map { pagingData ->
                pagingData.map { it.toNews() }
            }
    }
}