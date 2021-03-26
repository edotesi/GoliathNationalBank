package com.example.data.di

import javax.inject.Qualifier

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HerokuAppHttpClient

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitClient