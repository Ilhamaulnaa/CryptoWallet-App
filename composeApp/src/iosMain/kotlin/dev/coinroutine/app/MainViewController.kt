package dev.coinroutine.app

import androidx.compose.ui.window.ComposeUIViewController
import dev.coinroutine.app.di.initKoin

/*
Kode ini adalah pintu masuk (entry point) utama agar aplikasi Compose Multiplatform kamu bisa berjalan di perangkat iOS.
Dengan fungsi ini, kamu seolah-olah berkata pada iOS: "Ini adalah tampilan utama aplikasi saya, tolong tampilkan ini."
 */
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }