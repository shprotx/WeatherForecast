package ru.shprot.friendlyweather


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.shprot.friendlyweather.common.DataProvider
import ru.shprot.friendlyweather.ui.screens.MainScreen
import ru.shprot.friendlyweather.ui.screens.SplashScreen
import ru.shprot.friendlyweather.ui.theme.FriendlyWeatherTheme
import ru.shprot.friendlyweather.viewModel.MainViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var dataProvider: DataProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendlyWeatherTheme {
                Navigation(context = this, dataProvider = dataProvider)
            }
        }
    }
}




@Composable
fun Navigation(
    context: Context,
    dataProvider: DataProvider,
) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController, viewModel, context)
        }
        composable("main_screen") {
            MainScreen(context = context, dataProvider = dataProvider, viewModel)
        }
    }
}



