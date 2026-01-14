package dev.coinroutine.app.coins.presentation

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
data class CoinsState(

    val error: StringResource? = null,
    val isLoading: Boolean = false,
    val coins: List<UiCoinListItem> = emptyList(),
    val chartState: UiChartState? = null

)

@Stable
data class UiChartState(

    val sparkLine: List<Double> = emptyList(),
    val isLoading: Boolean,
    val coinName: String = ""

)
