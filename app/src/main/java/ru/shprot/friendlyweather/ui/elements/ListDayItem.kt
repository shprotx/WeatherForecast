package ru.shprot.friendlyweather.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.shprot.friendlyweather.R
import ru.shprot.friendlyweather.data.WeatherModel

@Composable
fun ListItem(item: WeatherModel, currentDay: MutableState<WeatherModel>) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .clickable {
                if (item.hours.isEmpty()) return@clickable
                currentDay.value = item
            },
        colors = CardDefaults.cardColors(colorResource(id = R.color.LightBlue))
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.padding(start = 8.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Text(text = item.time)
                Text(text = item.condition, color = Color.White)
            }

            Text(
                text = item.currentTemp.ifEmpty { "${item.maxTemp}/${item.minTemp}" },
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)
            )

            AsyncImage(
                model = "https:${item.icon}", // "//cdn.weatherapi.com/weather/64x64/night/302.png"
                contentDescription = "image2",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 8.dp)
            )
        }

    }
}










