package com.example.spinnercompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spinnercompose.myvariantspinner.MySpinner
import com.example.spinnercompose.skyvodesvariantspinner.SpinnerDemo
import com.example.spinnercompose.ui.theme.SpinnerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinnerComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val itemList = listOf("YouTube", "Telegram", "Instagram")
                        var selectedItem by remember { mutableStateOf(itemList[0]) }
                        MySpinner(
                            items = itemList,
                            selectedItem = selectedItem
                        ) {
                            selectedItem = it
                            Log.i("TAG", "ScreenInternal: $it")
                        }
                        Spacer(modifier = Modifier.padding(vertical = 20.dp))
                        SpinnerDemo()

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpinnerComposeTheme {
        Greeting("Android")
    }
}