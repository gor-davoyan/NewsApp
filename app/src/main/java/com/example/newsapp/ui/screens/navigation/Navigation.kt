package com.example.newsapp.ui.screens.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.ui.screens.article.ArticleScreen
import com.example.newsapp.ui.screens.news.NewsScreen
import com.example.newsapp.ui.screens.search.SearchScreen
import com.example.newsapp.ui.screens.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SplashScreen.name
    ) {
        composable(route = NavigationScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = NavigationScreens.NewsScreen.name) {
            NewsScreen(navController = navController)
        }

        composable(route = NavigationScreens.ArticleScreen.name) {
            navController.previousBackStackEntry?.arguments?.getParcelable(NAV_NEWS_ITEM, NewsItem::class.java)?.let { newsItem ->
                ArticleScreen(newsItem = newsItem)
            }
        }

        composable(route = NavigationScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}

sealed class NavigationScreens(val name: String) {
    data object SplashScreen : NavigationScreens("splashScreen")
    data object NewsScreen : NavigationScreens("newsScreen")
    data object ArticleScreen : NavigationScreens("articleScreen")
    data object SearchScreen : NavigationScreens("searchScreen")
}

const val NAV_NEWS_ITEM = "navNewsItem"
