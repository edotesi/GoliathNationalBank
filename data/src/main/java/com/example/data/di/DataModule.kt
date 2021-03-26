package com.example.data.di

import com.example.data.BuildConfig.BASE_URL
import com.example.data.repository.RateRepositoryImpl
import com.example.data.service.RatesService
import com.example.domain.repository.RateRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    private const val CONNECT_TIME_OUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @RetrofitClient
    fun provideRetrofit(@HerokuAppHttpClient okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @HerokuAppHttpClient
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().header("Accept", "application/json").build()
                )
            })
            .build()
    }

    @Provides
    fun provideRateService(@RetrofitClient retrofit: Retrofit): RatesService = retrofit.create(RatesService::class.java)

    @Provides
    @Singleton
    fun provideRateRepositoryImpl(ratesService: RatesService): RateRepository {
        return RateRepositoryImpl(ratesService)
    }

}