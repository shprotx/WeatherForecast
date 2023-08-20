package ru.shprot.friendlyweather.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.shprot.friendlyweather.R
import ru.shprot.friendlyweather.data.WeatherModel

@Composable
fun MainCard(
    currentDay: MutableState<WeatherModel>,
    onClickSync: () -> Unit,
    onClickSearch: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(start = 5.dp, top = 45.dp, end = 5.dp, bottom = 5.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.LightBlue))
        ) {

            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = currentDay.value.time,
                        style = TextStyle(fontSize = 15.sp),
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                    )

                    AsyncImage(
                        model = "https:${currentDay.value.icon}",
                        contentDescription = "image2",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 8.dp, top = 3.dp)
                    )

                }

                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White
                )
                Text(
                    text = if (currentDay.value.currentTemp.isNotEmpty())
                        currentDay.value.currentTemp.toFloat().toInt().toString() + "°C"
                    else "${currentDay.value.maxTemp.toFloat().toInt()}°C/${currentDay.value.minTemp.toFloat().toInt()}°C",
                    style = TextStyle(fontSize = 64.sp),
                    color = Color.White
                )
                Text(
                    text = currentDay.value.condition,
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(
                        onClick = {
                            onClickSearch.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "${currentDay.value.maxTemp.toFloat().toInt()}°C/${currentDay.value.minTemp.toFloat().toInt()}°C",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )

                    IconButton(
                        onClick = {
                            onClickSync.invoke()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_sync),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                }
            }

        }

    }
}