package dev.coinroutine.app.coins.presentation.component

import androidx.annotation.ColorLong
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PerformanceChart(
    modifier: Modifier = Modifier,
    nodes: List<Double>,
    profitColor: Color,
    lossColor: Color
) {

    if (nodes.isEmpty()) return
    /*
    max & min: Mencari nilai tertinggi dan terendah dari data (nodes).
    Ini sangat penting untuk melakukan normalisasi, agar grafik selalu pas dengan tinggi layar (tidak terlalu kecil atau terpotong).
     */
    val max = nodes.maxOrNull() ?: return
    val min = nodes.minOrNull() ?: return
    val lineColor = if (nodes.last() > nodes.first()) profitColor else lossColor

    Canvas(
        modifier = modifier.fillMaxWidth()
    ){
        //Path adalah objek yang mencatat urutan garis.
        //moveTo: Memindahkan kursor gambar ke titik awal tanpa membuat garis.
        //lineTo: Menghubungkan titik sebelumnya ke titik baru dengan garis lurus.
//        val path = Path()
//        nodes.forEachIndexed { index, value ->
//            //Lebar Canvas (size.width) dibagi dengan jumlah titik. Jadi, setiap titik akan memiliki jarak horizontal yang sama rata.
//            val x = index * (size.width / (nodes.size - 1))
//            /*((value - min) / (max - min)) menghasilkan angka antara 0.0 sampai 1.0 (posisi relatif data).
//            1 - ... digunakan karena pada sistem koordinat komputer, y = 0 adalah bagian atas. Kita ingin nilai tinggi berada di atas, maka kita balik hasilnya.
//            Hasilnya dikalikan dengan size.height untuk mendapatkan posisi piksel aslinya.
//            */
//            val y = size.height * (1 - ((value - min) / (max - min)).toFloat())
//
//            //Path adalah objek yang mencatat urutan garis.
//            if (index == 0){
//                //moveTo: Memindahkan kursor gambar ke titik awal tanpa membuat garis.
//                path.moveTo(x, y)
//            } else {
//                //lineTo: Menghubungkan titik sebelumnya ke titik baru dengan garis lurus.
////                path.lineTo(x, y)
//                path.lineTo(x, y)
//            }
//
//        }
//        /*
//        style = Stroke(...): Memberitahu Canvas untuk menggambar garis luarnya saja (bukan mengisi bentuk/fill).
//        3.dp.toPx(): Mengubah satuan DP menjadi Piksel agar ketebalan garis konsisten di berbagai kepadatan layar HP.
//         */
//        drawPath(
//            path = path,
//            color = lineColor,
//            style = Stroke(width = 3.dp.toPx())
//        )

    //Saran AI
    val path = Path()
    val distanceX = size.width / (nodes.size - 1)

    nodes.forEachIndexed { index, value ->
        val x = index * distanceX
        val y = size.height * (1 - ((value - min) / (max - min)).toFloat())

        if (index == 0) {
            path.moveTo(x, y)
        } else {
            // Ambil koordinat titik sebelumnya (x1, y1)
            val prevX = (index - 1) * distanceX
            val prevValue = nodes[index - 1]
            val prevY = size.height * (1 - ((prevValue - min) / (max - min)).toFloat())

            // Gunakan cubicTo untuk membuat lengkungan
            // Kita letakkan control point di tengah-tengah jarak X
            path.cubicTo(
                x1 = prevX + (x - prevX) / 2, y1 = prevY, // Control point 1
                x2 = prevX + (x - prevX) / 2, y2 = y,     // Control point 2
                x3 = x, y3 = y                            // Titik tujuan
            )
        }
    }

        // Buat salinan path untuk area fill (gradien)
        val fillPath = path
        fillPath.lineTo(size.width, size.height)
        fillPath.lineTo(0f, size.height)
        fillPath.close()

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(lineColor.copy(alpha = 1f), Color.Transparent)
            )
        )

    }

}