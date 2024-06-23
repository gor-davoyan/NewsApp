package com.example.newsapp.ui.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.ui.screens.component.NewsList
import com.example.newsapp.ui.screens.component.SearchItem

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchQuery by searchViewModel.searchQuery
    val searchNews = searchViewModel.searchedNews.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchItem(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchNews(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = { padding ->
            NewsList(
                navController = navController,
                news = searchNews,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    )
}
