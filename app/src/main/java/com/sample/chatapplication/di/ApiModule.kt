package com.sample.chatapplication.di

import android.content.Context
import com.sample.chatapplication.data.network.ApiService
import com.sample.chatapplication.data.repository.ChatRepository
import com.sample.chatapplication.util.AppUtils
import com.sample.chatapplication.viewmodel.ChatViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var okHttpClient: OkHttpClient

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService {
        interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        interceptor.level = HttpLoggingInterceptor.Level.NONE

        okHttpClient = OkHttpClient.Builder()
            //TODO : Enable Log
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
        okHttpClient.dispatcher.maxRequestsPerHost = 1
        return Retrofit.Builder()
            .baseUrl(AppUtils.mBaseUrl)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideChatRepository(backEndApi: ApiService): ChatRepository =
        ChatRepository(backEndApi)

    @Provides
    fun provideChatViewModel(
        feedRepository: ChatRepository,
        @ApplicationContext context: Context
    ): ChatViewModel =
        ChatViewModel(feedRepository)


}