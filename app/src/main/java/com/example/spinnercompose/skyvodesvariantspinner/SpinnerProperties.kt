package com.example.spinnercompose.skyvodesvariantspinner

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

data class SpinnerProperties(
  val color: Color = Color.Unspecified,
  val fontSize: TextUnit = TextUnit.Unspecified,
  val fontStyle: FontStyle? = null,
  val fontWeight: FontWeight? = null,
  val fontFamily: FontFamily? = null,
  val letterSpacing: TextUnit = TextUnit.Unspecified,
  val textDecoration: TextDecoration? = null,
  val textAlign: TextAlign? = null,
  val popupWidth: Dp? = null,
  val popupHeight: Dp? = null,
  val itemHeight: Dp? = null,
  val lineHeight: TextUnit = TextUnit.Unspecified,
  val overflow: TextOverflow = TextOverflow.Clip,
  val softWrap: Boolean = true,
  val maxLines: Int = Int.MAX_VALUE,
  val showDivider: Boolean = false,
  val dividerSize: Dp = 0.5.dp,
  val dividerColor: Color = Color.Unspecified,
  val spinnerPadding: Dp = 0.dp,
  val spinnerBackgroundColor: Color = Color.Unspecified
)
