package dev.coinroutine.app.coins.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.coinroutine.app.coins.domain.GetCoinsHistoryUseCase
import dev.coinroutine.app.coins.domain.GetCoinsListUseCase
import dev.coinroutine.app.core.domain.Result
import dev.coinroutine.app.core.util.formatFiat
import dev.coinroutine.app.core.util.formatPercentage
import dev.coinroutine.app.core.util.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsListViewModel (
    private val getCoinsListUseCase: GetCoinsListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CoinsState())
    val state = _state
        .onStart {
            getAllCoin()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CoinsState()
        )

    private suspend fun getAllCoin(){
        when(val responseCoin = getCoinsListUseCase.execute()){
            is Result.Success -> {
                _state.update {
                    CoinsState(
                        coins = responseCoin.data.map { modelCoin ->
                            UiCoinListItem(
                                id = modelCoin.coin.id,
                                name = modelCoin.coin.name,
                                symbol = modelCoin.coin.symbol,
                                iconUrl = modelCoin.coin.iconUrl,
                                formattedPrice = formatFiat(modelCoin.price),
                                formattedChange = formatPercentage(modelCoin.change),
                                isPositive = modelCoin.change >= 0
                            )
                        }
                    )
                }
            }
            is Result.Error -> {
                _state.update {
                    it.copy(
                        coins = emptyList(),
                        error = responseCoin.error.toUiText()
                    )
                }
            }
        }
    }

}