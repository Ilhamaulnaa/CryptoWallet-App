package dev.coinroutine.app.coins.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coinroutine.composeapp.generated.resources.Res
import coinroutine.composeapp.generated.resources.background_banner
import coinroutine.composeapp.generated.resources.compose_multiplatform
import dev.coinroutine.app.theme.CoinRoutineTheme
import dev.coinroutine.app.theme.InversePrimaryDark
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBaseBar(
    modifier: Modifier = Modifier,
    text: String
){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
        contentAlignment = Alignment.CenterStart
    ){
        Image(
            painter = painterResource(resource = Res.drawable.background_banner),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.background,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }

}

@Preview
@Composable
fun TopAppBaseBarPreview(){
    CoinRoutineTheme {
        Surface {
            TopAppBaseBar(text = "Top Coin:")
        }
    }
}