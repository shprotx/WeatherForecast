package ru.shprot.friendlyweather.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.shprot.friendlyweather.data.WeatherModel
import javax.inject.Inject
import javax.inject.Singleton

class MainViewModel: ViewModel() {

    val selectedCity = mutableStateOf("")
    val daysList = mutableStateOf(listOf<WeatherModel>())
    val currentDay = mutableStateOf(
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
    )
}