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
import ru.shprot.friendlyweather.ui.elements.MainCard
import ru.shprot.friendlyweather.ui.elements.TabLayout
import ru.shprot.friendlyweather.viewModel.MainViewModel


@Composable
fun MainScreen(
    context: Context,
    dataProvider: DataProvider,
    viewModel: MainViewModel
) {
    val dialogState = remember { mutableStateOf(false) }

    if (dialogState.value) JustDialogSearch(dialogState, onSubmit = {
        dataProvider.getData(
            it,
            context,
            viewModel.daysList,
            viewModel.currentDay)
    })

    dataProvider.getData(
        viewModel.selectedCity.value,
        context,
        viewModel.daysList,
        viewModel.currentDay)

    Image(
        painter = painterResource(id = R.drawable.sky_bg),
        contentDescription = "",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Column {
        MainCard(
            viewModel.currentDay,
            onClickSync = {
                dataProvider.getData(
                    viewModel.selectedCity.value,
                    context, viewModel.daysList,
                    viewModel.currentDay)
            },
            onClickSearch = {
                dialogState.value = true
            })
        TabLayout(
            viewModel.daysList,
            viewModel.currentDay
        )
    }
}









