package com.example.newsapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.newsapp.data.entity.NewsEntity
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.mappers.toNewsEntity
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val newsDb: NewsDatabase,
    private val newsApi: NewsApi,
): RemoteMediator<Int, NewsEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val news = newsApi.getNews(page = loadKey, pageCount = state.config.pageSize)

            newsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDb.dao.clearAll()
                }

                val newsEntities = news.response.results.map { it.toNewsEntity(news.response) }
                newsDb.dao.upsertAll(newsEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = news.response.results.isEmpty()
            )

        }catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }

    }
}
