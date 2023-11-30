package com.example.spinnercompose.myvariantspinner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> Spinner(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    selectedItemFactory: @Composable (Modifier, T) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
    onDropdownStateChanged: (Boolean) -> Unit
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
            .background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        selectedItemFactory(
            Modifier
                .clickable { expanded = !expanded },
            selectedItem
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onDropdownStateChanged(false)
            },
            modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(
                    text = {
                        dropdownItemFactory(element, index)
                    },
                    onClick = {
                        onItemSelected(items[index])
                        expanded = false
                        onDropdownStateChanged(false)
                    })
            }
        }
    }

    DisposableEffect(expanded) {
        onDropdownStateChanged(expanded)
        onDispose { }
    }
}
