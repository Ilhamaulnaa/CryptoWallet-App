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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.coinroutine.app.coins.presentation.component.LoadingNeedSection
import dev.coinroutine.app.coins.presentation.component.PerformanceChart
import dev.coinroutine.app.coins.presentation.component.TopAppBaseBar
import dev.coinroutine.app.theme.CoinRoutineTheme
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
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            when {
                state.isLoading && state.coins.isEmpty() -> {
                    LoadingNeedSection()
                } else -> {
                    CoinsListContent(
                        state = state,
                        onDismissClick = { coinsListViewModel.onDismissChart() },
                        onCoinLongPressed = { coinsListViewModel.onCoinLongPressed(it) },
                        onCoinClicked = onCoinClicked
                    )
                }
            }
            /*
            AnimatedVisibility(
                visible = state.isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ){
                LoadingNeedSection()
            }
            AnimatedVisibility(
                visible = state.coins.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ){
                Column{
                    CoinsListContent(
                        state = state,
                        onDismissClick = { coinsListViewModel.onDismissChart() },
                        onCoinLongPressed = { coinId -> coinsListViewModel.onCoinLongPressed(coinId) },
                        onCoinClicked = onCoinClicked
                    )
                }
            }
             */

            //saran ai, untuk perpindahan Loading -> Success -> Error,
            /*
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Crossfade(
                targetState = state.isLoading,
                animationSpec = tween(durationMillis = 500) // Durasi transisi halus
            ) { loading ->
                if (loading && state.coins.isEmpty()) {
                    LoadingNeedSection() // Shimmer
                } else {
                    CoinsListContent(
                        state = state,
                        onDismissClick = { coinsListViewModel.onDismissChart() },
                        onCoinLongPressed = { coinId -> coinsListViewModel.onCoinLongPressed(coinId) },
                        onCoinClicked = onCoinClicked
                    )
                }
            }
        }
             */
        }
    }

}

@Composable
fun CoinsListContent(
    state: CoinsState,
    onDismissClick: () -> Unit,
    onCoinLongPressed: (String) -> Unit,
    onCoinClicked: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (state.chartState != null){
            CoinChartDialog(
                uiChartState = state.chartState,
                onDismissClick = onDismissClick
            )
        }
        CoinsList(
            coins = state.coins,
            onCoinClicked = onCoinClicked,
            onCoinLongPressed = onCoinLongPressed
        )
    }
}

@Composable
fun CoinsList(
    coins: List<UiCoinListItem>,
    onCoinLongPressed: (String) -> Unit,
    onCoinClicked: (String) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = WindowInsets.systemBars.asPaddingValues(),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onCoinClicked = onCoinClicked,
                    onCoinLongPressed = onCoinLongPressed,
                    modifier = Modifier
                        .fillMaxWidth()
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
    onCoinLongPressed: (String) -> Unit,
    onCoinClicked: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = {
                    onCoinLongPressed(coin.id)
                },
                onClick = {
                    onCoinClicked(coin.id)
                }
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
fun CoinChartDialog(
    uiChartState: UiChartState,
    onDismissClick: () -> Unit
){

    val titleDialog = buildAnnotatedString {
        append("24h price chart for ")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
            append(uiChartState.coinName)
        }
    }

    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = onDismissClick,
        title = {
            Text(
                text = titleDialog
            )
        },
        text = {
            if (uiChartState.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            } else {
                PerformanceChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    nodes = uiChartState.sparkLine,
                    profitColor = LocalCoinRoutineColorsPalette.current.profitGreen,
                    lossColor = LocalCoinRoutineColorsPalette.current.lossRed
                )
            }
        },
        confirmButton = {},
        dismissButton = {
            Button(
                onClick = onDismissClick
            ){
                Text(
                    text = "Close"
                )
            }
        }
    )

}

@Preview
@Composable
fun PreviewCoinChartDialog(){
    CoinRoutineTheme {
        Surface {
            val uiCharState = UiChartState(
                isLoading = false,
                coinName = "Etherume"
            )
            CoinChartDialog(
                uiChartState = uiCharState,
                onDismissClick = {}
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
                onCoinClicked = {},
                onCoinLongPressed = {}
            )
        }
    }
}