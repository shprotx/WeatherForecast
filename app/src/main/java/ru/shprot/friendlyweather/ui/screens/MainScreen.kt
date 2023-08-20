package ru.shprot.friendlyweather.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.shprot.friendlyweather.R
import ru.shprot.friendlyweather.common.DataProvider
import ru.shprot.friendlyweather.data.WeatherModel
import ru.shprot.friendlyweather.ui.elements.MainCard
import ru.shprot.friendlyweather.ui.elements.TabLayout


@Composable
fun MainScreen(
    context: Context,
    dataProvider: DataProvider
) {
    val daysList = remember { mutableStateOf(listOf<WeatherModel>()) }
    val dialogState = remember { mutableStateOf(false) }

    val currentDay = remember { mutableStateOf(
        WeatherModel(
        "",
        "",
        "0",
        "",
        "",
        "0",
        "0",
        "",
    )
    ) }
    if (dialogState.value) JustDialogSearch(dialogState, onSubmit = {
        dataProvider.getData(it, context, daysList, currentDay)
    })
    dataProvider.getData("London", context, daysList, currentDay)

    Image(
        painter = painterResource(id = R.drawable.sky_bg),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Column {
        MainCard(
            currentDay,
            onClickSync = {
                dataProvider.getData("London", context, daysList, currentDay)
            },
            onClickSearch = {
                dialogState.value = true
            })
        TabLayout(daysList, currentDay)
    }
}









