package com.example.newsapp

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.testing.asSnapshot
import com.example.newsapp.data.entity.NewsEntity
import com.example.newsapp.data.mappers.toNews
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.domain.usecase.NewsUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsUseCaseImplTest {

    private val newsItems = listOf(
        NewsEntity(1, "testTitle1", "testDescription1", "testDate1", "textThumbnail1"),
        NewsEntity(2, "testTitle2", "testdescription2", "testDate2", "textThumbnail2")
    )

    @MockK
    private lateinit var newsRepository: NewsRepository

    private lateinit var newsUseCase: NewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        newsUseCase = NewsUseCaseImpl(newsRepository)
    }

    @Test
    fun test1() = runTest {
        val testData = PagingData.from(newsItems)
        coEvery { newsRepository.getNews() } returns flowOf(testData)

        val expectedResult = flowOf(testData).asSnapshot().map { it.toNews() }

        val result = newsUseCase.getNews().asSnapshot()

        assertEquals(expectedResult, result)
    }

    @Test
    fun test2() = runTest {
        val query = "testTitle1"
        val testData = PagingData.from(newsItems)
        coEvery { newsRepository.searchNews(query) } returns flowOf(testData.filter { it.title.contains(query) })

        val expectedResult = flowOf(testData).asSnapshot().map { it.toNews() }.first()

        val result = newsUseCase.searchNews(query).asSnapshot()

        assertEquals(listOf(expectedResult), result)
    }
}