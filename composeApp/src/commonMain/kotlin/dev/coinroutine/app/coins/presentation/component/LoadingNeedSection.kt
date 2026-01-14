package dev.coinroutine.app.coins.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dev.coinroutine.app.theme.CoinRoutineTheme
import dev.coinroutine.app.theme.InverseSurfaceDark
import dev.coinroutine.app.theme.ScrimDark
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingNeedSection(
    modifier: Modifier = Modifier,
    size: Int = 10
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .shimmer()
    ) {
        items(size) {
            LoadingItem()
        }
    }
}

@Composable
fun LoadingItem(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Lingkaran untuk Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.6f))
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Kolom untuk Teks (Nama & Simbol)
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f) // Panjang bar teks 60%
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.6f))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f) // Panjang bar teks 30%
                    .height(12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.6f))
            )
        }

        // Bagian Harga di Kanan
        Column(horizontalAlignment = Alignment.End) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(14.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.6f))
            )
        }
    }
}

/*
@Composable
fun LoadingNeedSection(
    modifier: Modifier = Modifier,
    size: Int = 10
){

    Column(modifier = modifier.shimmer()){
        repeat(size){
            LoadingItem()
            Spacer(modifier = Modifier.height(2.dp))
        }
    }

}

@Composable
fun LoadingItem(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 0.3.dp,
                color = Color(0xFF4D4D4D),
                shape = RoundedCornerShape(12.dp)
            )
            .height(20.dp)
            .background(ScrimDark)
    )

}
 */

@Preview
@Composable
fun PreviewLoadingNeedSection(){
    CoinRoutineTheme {
        Surface {
            LoadingNeedSection(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}