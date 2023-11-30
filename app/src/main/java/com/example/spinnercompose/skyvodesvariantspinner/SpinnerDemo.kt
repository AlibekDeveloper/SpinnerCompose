package com.example.spinnercompose.skyvodesvariantspinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.spinnercompose.R
import com.example.spinnercompose.ui.theme.black
import com.example.spinnercompose.ui.theme.gray
import com.example.spinnercompose.ui.theme.spinner

@Composable
fun SpinnerDemo(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = modifier.padding(4.dp)) {
            val (selectedItem0, setSelectedItem0) = remember { mutableStateOf("Choose a question") }
            Spinner<String>(
                text = selectedItem0,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(spinner)
                    .align(Alignment.CenterHorizontally),
                itemListRes = R.array.list_spinner,
                properties = SpinnerProperties(
                    color = black,
                    textAlign = TextAlign.Start,
                    itemHeight = 50.dp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    spinnerPadding = 16.dp,
                    spinnerBackgroundColor = gray
                ),
                onSpinnerItemSelected = { _, item ->
                    setSelectedItem0(item)
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            val coffeeList = remember { listOf("Americano", "Cold Brew", "Espresso", "Latte") }
            val (selectedItem1, setSelectedItem1) = remember { mutableStateOf("Choose your coffee") }
            Spinner(
                text = selectedItem1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .background(spinner)
                    .align(Alignment.CenterHorizontally),
                itemList = coffeeList,
                properties = SpinnerProperties(
                    color = black,
                    itemHeight = 50.dp,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    spinnerPadding = 16.dp,
                    spinnerBackgroundColor = gray
                ),
                onSpinnerItemSelected = { _, item ->
                    setSelectedItem1(item)
                }
            )
        }
    }
}
