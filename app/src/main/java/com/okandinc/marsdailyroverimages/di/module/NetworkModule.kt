package com.okandinc.marsdailyroverimages.di.module

import com.okandinc.marsdailyroverimages.api.MarsRoverImageService
import com.okandinc.marsdailyroverimages.utils.API_KEY
import com.okandinc.marsdailyroverimages.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .addInterceptor{chain ->
                    val request = chain.request()
                    val httpUrl = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
                    val newRequest = request.newBuilder().url(httpUrl).build()
                    chain.proceed(newRequest)
                }.build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMarsRoverImageService(retrofit: Retrofit) : MarsRoverImageService {
        return retrofit.create(MarsRoverImageService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return retrofit2.Retrofit.Builder()
               .client(okHttpClient)
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
               .baseUrl(BASE_URL)
               .build()
    }
}