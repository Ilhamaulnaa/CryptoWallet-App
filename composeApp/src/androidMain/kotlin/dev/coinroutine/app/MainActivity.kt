package dev.coinroutine.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.coinroutine.app.coins.presentation.CoinsListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
//            CoinsListScreen(
//                onCoinClicked = {}
//            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
//    CoinsListScreen(
//        onCoinClicked = {}
//    )
}