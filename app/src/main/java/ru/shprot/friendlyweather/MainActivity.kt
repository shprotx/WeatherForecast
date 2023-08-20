package ru.shprot.friendlyweather


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.shprot.friendlyweather.common.DataProvider
import ru.shprot.friendlyweather.ui.screens.MainScreen
import ru.shprot.friendlyweather.ui.theme.FriendlyWeatherTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var dataProvider: DataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendlyWeatherTheme {
                MainScreen(context = this, dataProvider)
            }
        }
    }
}