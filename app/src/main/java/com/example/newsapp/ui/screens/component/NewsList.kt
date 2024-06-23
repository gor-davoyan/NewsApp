package com.example.newsapp.ui.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.ui.screens.navigation.NAV_NEWS_ITEM
import com.example.newsapp.ui.screens.navigation.NavigationScreens
import com.example.newsapp.ui.theme.HalfPadding

@Composable
fun NewsList(
    navController: NavController,
    news: LazyPagingItems<NewsItem>,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(start = HalfPadding, end = HalfPadding)
    ) {
        if (news.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(news.itemCount) { index ->
                    val newsItem = news[index]
                    newsItem?.let {
                        NewsArticle(newsItem = newsItem) {
                            navController.currentBackStackEntry?.arguments?.putParcelable(
                                NAV_NEWS_ITEM,
                                newsItem
                            )
                            navController.navigate(NavigationScreens.ArticleScreen.name)
                        }
                    }
                }
                item {
                    if (news.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                    if (news.loadState.append is LoadState.Error) {
                        val error = news.loadState.append as LoadState.Error
                        Text(
                            text = "Error: ${error.error.message}",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(HalfPadding)
                        )
                    }
                }
            }
        }
    }
}
