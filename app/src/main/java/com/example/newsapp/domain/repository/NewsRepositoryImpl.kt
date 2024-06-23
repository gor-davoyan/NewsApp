package com.example.newsapp.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.entity.NewsEntity
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsRemoteMediator
import com.example.newsapp.data.remote.SearchPagingSource
import com.example.newsapp.util.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDb: NewsDatabase,
) : NewsRepository {

    override fun getNews(): Flow<PagingData<NewsEntity>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = NewsRemoteMediator(
                newsDb = newsDb,
                newsApi = newsApi,
            ),
            pagingSourceFactory = {
                newsDb.dao.pagingSource()
            }
        ).flow
    }

    override fun searchNews(query: String): Flow<PagingData<NewsEntity>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(
                    newsApi = newsApi,
                    query = query
                )
            }
        ).flow
    }
}