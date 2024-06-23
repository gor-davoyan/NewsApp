package com.example.newsapp.data.remote

import com.example.newsapp.data.entity.NewsResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/search")
    suspend fun getNews(
        @Query("show-fields") showFields: String = "thumbnail,body-text",
        @Query("page") page: Int,
        @Query("page-size") pageCount: Int
    ): NewsResponseWrapper

    @GET("/search")
    suspend fun searchNews(
        @Query("show-fields") showFields: String = "thumbnail,body-text",
        @Query("q") query: String,
        @Query("page-size") pageCount: Int
    ): NewsResponseWrapper
}