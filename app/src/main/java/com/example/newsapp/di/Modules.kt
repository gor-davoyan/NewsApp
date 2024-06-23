package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.domain.usecase.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://content.guardianapis.com"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("api-key", BuildConfig.API_KEY).build()
            chain.proceed(request)
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModules {
    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModules {
    @Binds
    abstract fun bindNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl): NewsUseCase
}