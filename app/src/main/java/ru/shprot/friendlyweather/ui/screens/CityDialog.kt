package ru.shprot.friendlyweather.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.shprot.friendlyweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JustDialogSearch(
    dialogState: MutableState<Boolean>,
    onSubmit: (String) -> Unit
) {
    val dialogText = remember { mutableStateOf("") }
    val checkBoxState = remember { mutableStateOf(true) }

    Dialog(
        onDismissRequest = { dialogState.value = false },
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.sky_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.LightBlue))
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = "Введите название города:",
                    color = Color.White
                )
                TextField(
                    value = dialogText.value,
                    onValueChange = { dialogText.value = it },
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkBoxState.value,
                        onCheckedChange = { checkBoxState.value = !checkBoxState.value },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.White,
                            checkedColor = Color.White,
                            checkmarkColor = colorResource(id = R.color.blue)
                        )
                    )
                    Text(
                        text = "Сохранить по умолчанию",
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(
                        onClick = { dialogState.value = false },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                        )
                    ) {
                        Text(text = "Отмена")
                    }
                    Button(
                        onClick = {
                            onSubmit(dialogText.value)
                            dialogState.value = false
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = colorResource(id = R.color.blue),
                            containerColor = Color.White
                        )
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }

}