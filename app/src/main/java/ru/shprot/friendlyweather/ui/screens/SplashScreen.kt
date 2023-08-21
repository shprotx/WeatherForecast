package ru.shprot.friendlyweather.ui.screens


import android.Manifest
import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.shprot.friendlyweather.R
import ru.shprot.friendlyweather.common.DataProvider
import ru.shprot.friendlyweather.common.LocationDetector
import ru.shprot.friendlyweather.viewModel.MainViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MainViewModel,
    context: Context,
    defaultCity: String,
    dataProvider: DataProvider
) {
    ShowSplashScreenUI()

    if (defaultCity.isNotEmpty()) {
        dataProvider.getData(
            defaultCity,
            context,
            viewModel.daysList,
            viewModel.currentDay,
            successResult = { openMainScreen(navController) }
        )
    } else {
        val permissionState = rememberMultiplePermissionsState(permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ), onPermissionsResult = { permissionStateMap ->
            if (!permissionStateMap.containsValue(false)) {
                determineCurrentCity(context, viewModel, navController, dataProvider)
            } else {
                dataProvider.getData(
                    "Moscow",
                    context,
                    viewModel.daysList,
                    viewModel.currentDay,
                    successResult = { openMainScreen(navController) }
                )
            }
        })

        LaunchedEffect(permissionState) {
            if (!permissionState.allPermissionsGranted)
                permissionState.launchMultiplePermissionRequest()
            else
                determineCurrentCity(context, viewModel, navController, dataProvider)
        }


    }

}


private fun determineCurrentCity(
    context: Context,
    viewModel: MainViewModel,
    navController: NavController,
    dataProvider: DataProvider
) {
    LocationDetector().getCurrentCity(context) { city ->
        dataProvider.getData(
            city,
            context,
            viewModel.daysList,
            viewModel.currentDay,
            successResult = { openMainScreen(navController) }
        )
    }
}


private fun openMainScreen(navController: NavController) {
    CoroutineScope(Dispatchers.Main).launch {
        delay(1000)
        navController.navigate("main_screen")
    }
}


@Composable
fun ShowSplashScreenUI() {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sky_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.LightBlue))
        ) {
            Column(
                modifier = Modifier.width(200.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sun_center),
                        contentDescription = null,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_sun_lines),
                        contentDescription = null,
                        modifier = Modifier.graphicsLayer(rotationZ = rotation)
                    )
                }
                Text(
                    text = "Загружаем...",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

    }

}








