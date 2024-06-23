package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.entity.NewsEntity
import com.example.newsapp.data.mappers.toNewsEntity
import com.example.newsapp.util.ITEMS_PER_PAGE

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val query: String
): PagingSource<Int, NewsEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = newsApi.searchNews(query = query, pageCount = ITEMS_PER_PAGE)
            val newsEntities = response.response.results.map { it.toNewsEntity(response.response) }
            val endOfPaginationReached = newsEntities.isEmpty()
            if (response.response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = newsEntities,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, NewsEntity>): Int? {
        return state.anchorPosition
    }
}
