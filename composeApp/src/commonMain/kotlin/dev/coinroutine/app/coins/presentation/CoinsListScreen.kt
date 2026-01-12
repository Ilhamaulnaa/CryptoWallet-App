package dev.coinroutine.app.coins.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coinroutine.composeapp.generated.resources.Res
import coinroutine.composeapp.generated.resources.background_banner
import coinroutine.composeapp.generated.resources.compose_multiplatform
import dev.coinroutine.app.coins.presentation.component.TopAppBaseBar
import dev.coinroutine.app.theme.CoinRoutineTheme
import dev.coinroutine.app.theme.InversePrimaryDark
import dev.coinroutine.app.theme.ListEven
import dev.coinroutine.app.theme.ListODD
import dev.coinroutine.app.theme.LocalCoinRoutineColorsPalette
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsListScreen(
    onCoinClicked: (String) -> Unit,
) {

    val coinsListViewModel = koinViewModel<CoinsListViewModel>()
    val state by coinsListViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBaseBar(
                text = "🔥 Top Coins:"
            )
        },
    ){
        Column(
            modifier =
                Modifier.padding(top = 72.dp)
        ){
            CoinsListContent(
                state = state,
                onCoinClicked = onCoinClicked
            )
        }
    }

}

@Composable
fun CoinsListContent(
    state: CoinsState,
    onCoinClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CoinsList(
            coins = state.coins,
            onCoinClicked = onCoinClicked
        )
    }
}

@Composable
fun CoinsList(
    coins: List<UiCoinListItem>,
    onCoinClicked: (String) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
//            .background(Color(0xFF004E94))
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = WindowInsets.systemBars.asPaddingValues(),
            modifier = Modifier.fillMaxSize(),
        ) {
            itemsIndexed(coins) { index, coin ->
//                val backgroundColor = if (index % 2 == 0){
//                    MaterialTheme.colorScheme.primary
//                } else {
//                    MaterialTheme.colorScheme.onPrimary
//                }
                CoinListItem(
                    coin = coin,
//                    onCoinLongPressed = onCoinLongPressed,
                    onCoinClicked = onCoinClicked,
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(backgroundColor)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CoinListItem(
    modifier: Modifier = Modifier,
    coin: UiCoinListItem,
//    onCoinLongPressed: (String) -> Unit,
    onCoinClicked: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
//                onLongClick = { onCoinLongPressed(coin.id) },
                onClick = { onCoinClicked(coin.id) }
            )
            .padding(16.dp)
    ) {
        AsyncImage(
            model = coin.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(4.dp).clip(CircleShape).size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coin.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coin.symbol,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = coin.formattedPrice,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coin.formattedChange,
                color = if (coin.isPositive) LocalCoinRoutineColorsPalette.current.profitGreen else LocalCoinRoutineColorsPalette.current.lossRed,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            )
        }
    }
}

@Composable
@Preview
fun CoinListItemPreview(){
    CoinRoutineTheme {
        Surface {
            val coin = UiCoinListItem(
                id = "1",
                name = "Etherum",
                symbol = "ETH",
                iconUrl = "https://tse2.mm.bing.net/th/id/OIP.F1YPm992wjDpFkvr8Y9gfQHaE8?rs=1&pid=ImgDetMain&o=7&rm=3",
                formattedPrice = "20_000_000",
                formattedChange = "09.92",
                isPositive = true
            )
            CoinListItem(
                coin = coin,
                onCoinClicked = {}
            )
        }
    }
}