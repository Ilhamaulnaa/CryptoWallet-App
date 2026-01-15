package dev.coinroutine.app.di

import androidx.room.RoomDatabase
import dev.coinroutine.app.core.database.portfolio.PortfolioDatabase
import dev.coinroutine.app.core.database.portfolio.getPortfolioDatabase
import dev.coinroutine.app.core.database.portfolio.getPortfolioDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*
Ini adalah pasangan dari expect. Karena kamu sudah berjanji di folder commonMain bahwa akan ada platformModule,
maka di folder iosMain kamu harus menggunakan kata kunci actual untuk memberikan isi atau implementasi nyata dari janji tersebut.
Darwin adalah nama mesin (engine) HTTP yang dikembangkan oleh Apple
 */
actual val platformModule = module {
    single<HttpClientEngine>{ Darwin.create() }

    singleOf(::getPortfolioDatabaseBuilder).bind<RoomDatabase.Builder<PortfolioDatabase>>()

}