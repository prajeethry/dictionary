package com.nike.coding.dictionary.network.module

import com.nike.coding.dictionary.network.DictionaryDefinitionsProvider
import com.nike.coding.dictionary.network.HeaderInterceptor
import com.nike.coding.dictionary.network.RapidApiService
import com.nike.coding.dictionary.network.SchedulerHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DictionaryNetworkModule {

    companion object {
        private const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(HeaderInterceptor())
            .build()

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideRapidApiService(retrofit: Retrofit): RapidApiService = retrofit.create(RapidApiService::class.java)

    @Provides
    fun provideSchedulerHelper() = SchedulerHelper()

    @Provides
    fun provideDictionaryDefinitionsProvider(rapidApiService: RapidApiService, schedulerHelper: SchedulerHelper) = DictionaryDefinitionsProvider(rapidApiService, schedulerHelper)
}