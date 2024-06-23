package com.example.newsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsapp.data.entity.NewsEntity

@Dao
interface NewsDao {

    @Upsert
    suspend fun upsertAll(news: List<NewsEntity>)

    @Query("SELECT * FROM newsentity")
    fun pagingSource(): PagingSource<Int, NewsEntity>

    @Query("DELETE FROM newsentity")
    suspend fun clearAll()
}