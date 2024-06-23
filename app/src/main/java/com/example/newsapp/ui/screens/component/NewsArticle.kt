package com.example.newsapp.ui.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.data.entity.Fields
import com.example.newsapp.data.entity.NewsItem
import com.example.newsapp.ui.theme.HalfPadding
import com.example.newsapp.ui.theme.NewsCardItemElevation
import com.example.newsapp.util.toReadableDate

@Composable
fun NewsArticle(
    newsItem: NewsItem,
    onItemClick: () -> Unit
) {
    Card(
        onClick = {
            onItemClick()
        },
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(NewsCardItemElevation)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(HalfPadding)
        ) {
            AsyncImage(
                model = newsItem.fields.thumbnail,
                modifier = Modifier
                    .height(128.dp)
                    .padding(end = HalfPadding)
                    .weight(1f),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = newsItem.webTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                Text(
                    text = newsItem.webPublicationDate.toReadableDate(),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsArticleItem() {
    val article = NewsItem(
        index = 1,
        webPublicationDate = "2024-06-18T22:46:08Z",
        webTitle = "Australia news live: police investigate vandalism of Josh Burns’ St Kilda office; Peter Dutton to unveil nuclear plans",
        fields = Fields(
            bodyText = "Australia news live: police investigate vandalism of Josh Burns’ St Kilda office; Peter Dutton to unveil nuclear plans Australia news live: police investigate vandalism of Josh Burns’ St Kilda office; Peter Dutton to unveil nuclear plans Australia news live: police investigate vandalism of Josh Burns’ St Kilda office; Peter Dutton to unveil nuclear plans",
            thumbnail = "https://media.guim.co.uk/953613a287ea14e076ef67da532c3cfcdf7dfa3b/0_0_6266_3762/500.jpg"
        )
    )
    NewsArticle(newsItem = article, {})
}
