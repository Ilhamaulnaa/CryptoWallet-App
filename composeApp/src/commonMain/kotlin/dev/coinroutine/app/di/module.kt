package dev.coinroutine.app.di

import dev.coinroutine.app.coins.data.remote.impl.KtorCoinsRemoteDataSource
import dev.coinroutine.app.coins.domain.GetCoinDetailsUseCase
import dev.coinroutine.app.coins.domain.GetCoinsHistoryUseCase
import dev.coinroutine.app.coins.domain.GetCoinsListUseCase
import dev.coinroutine.app.coins.domain.api.CoinsRemoteDataSource
import dev.coinroutine.app.coins.presentation.CoinsListViewModel
import dev.coinroutine.app.core.network.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

//Fungsi ini adalah "pintu masuk" utama untuk menyalakan Koin.
fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedPlatform,
            platformModule
        )
    }

/*Tujuan: Karena ada beberapa komponen yang cara pembuatannya berbeda di tiap OS (misalnya Database Driver atau Ktor Engine),
kita memberi tahu Kotlin: "Saya berjanji akan menyediakan implementasi platformModules di folder androidMain dan iosMain secara terpisah."
*/
expect val platformModule : Module

//Di sinilah letak logika pembuatan objek yang sama untuk semua platform.
val sharedPlatform = module {

    //core (Networking)
    single<HttpClient>{ HttpClientFactory.create(get()) }

    //coin list
    viewModel { CoinsListViewModel(get(), get()) }
    //singleOf sama saja seperti single{GetCoinsListUseCase(get())}
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinsRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
    singleOf(::GetCoinsHistoryUseCase)

}

