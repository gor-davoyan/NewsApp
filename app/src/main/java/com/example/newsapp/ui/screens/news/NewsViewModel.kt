package com.example.newsapp.ui.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    newsUseCase: NewsUseCase,
) : ViewModel() {

    val news: Flow<PagingData<NewsItem>> = newsUseCase
        .getNews()
        .cachedIn(viewModelScope)
}
