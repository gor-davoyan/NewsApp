package com.example.newsapp.ui.screens.article

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.ui.theme.ContentTextPadding
import com.example.newsapp.ui.theme.HalfPadding
import com.example.newsapp.util.TOP_BAR_TEXT_NEWS
import com.example.newsapp.util.toReadableDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleScreen(
    newsItem: NewsItem
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = TOP_BAR_TEXT_NEWS)
                }
            )
        },
        content = {
            ArticleScreenContent(
                newsItem = newsItem,
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
fun ArticleScreenContent(
    newsItem: NewsItem,
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .padding(start = HalfPadding)
            .verticalScroll(rememberScrollState())
//            .height(IntrinsicSize.Max)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = newsItem.webTitle,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(HalfPadding),
            model = newsItem.fields.thumbnail,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        Text(
            modifier = Modifier
                .padding(start = ContentTextPadding, end = ContentTextPadding)
                .align(Alignment.CenterHorizontally),
            text = newsItem.fields.bodyText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(HalfPadding))

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(start = ContentTextPadding, end = ContentTextPadding)
                .align(Alignment.CenterHorizontally),
            text = newsItem.webPublicationDate.toReadableDate(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
