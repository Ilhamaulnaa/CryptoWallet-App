package dev.coinroutine.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine>{ Android.create() }
}