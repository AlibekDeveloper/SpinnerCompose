package com.example.spinnercompose.skyvodesvariantspinner

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ArrayRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import com.example.spinnercompose.R
import com.skydoves.powerspinner.PowerSpinnerView
import com.skydoves.powerspinner.SpinnerAnimation
import com.skydoves.powerspinner.createPowerSpinnerView

@Composable
fun <T> Spinner(
    modifier: Modifier = Modifier,
    text: String = "",
    properties: SpinnerProperties = SpinnerProperties(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    style: TextStyle = LocalTextStyle.current,
    onSpinnerItemSelected: (Int, T) -> Unit = { _, _ -> },
    onSpinnerOutsideTouched: (View, MotionEvent) -> Unit = { _, _ -> },
    dismissWhenNotifiedItemSelected: Boolean = true,
    spinnerAnimation: SpinnerAnimation = SpinnerAnimation.NORMAL,
    @ArrayRes itemListRes: Int? = null,
    itemList: List<T> = listOf(),
    update: (PowerSpinnerView) -> Unit = {}
) {
    val spinnerView = remember(properties) {
        createPowerSpinnerView(context) {
            setShowArrow(false)
            setShowDivider(properties.showDivider)
            setDividerColor(properties.dividerColor.toArgb())
            setDividerSize(context.dp2Px(properties.dividerSize))
            setLifecycleOwner(lifecycleOwner)
            setSpinnerPopupAnimation(spinnerAnimation)
            setSpinnerPopupBackground(ColorDrawable(properties.spinnerBackgroundColor.toArgb()))
            setDismissWhenNotifiedItemSelected(dismissWhenNotifiedItemSelected)
            setOnSpinnerOutsideTouchListener { view, motionEvent ->
                onSpinnerOutsideTouched(view, motionEvent)
            }
            properties.popupWidth?.let { setSpinnerPopupWidth(context.dp2Px(it)) }
            properties.popupHeight?.let { setSpinnerPopupHeight(context.dp2Px(it)) }
            properties.itemHeight?.let { setSpinnerItemHeight(context.dp2Px(it)) }
        }
    }
    with(spinnerView) {
        itemListRes?.let { setItems(it) } ?: setItems(itemList)
        setIsFocusable(true)
        setTextColor(
            if (properties.color != Color.Unspecified) {
                properties.color.toArgb()
            } else {
                style.color.toArgb()
            }
        )
        textSize = if (properties.fontSize != TextUnit.Unspecified) {
            properties.fontSize.value
        } else {
            style.fontSize.value
        }
        properties.textAlign?.let { gravity = it.toGravity() }
        val padding = properties.spinnerPadding.value.toInt()
        setPadding(padding, padding, padding, padding)
        spinnerView.setDisableChangeTextWhenNotified(true)
        spinnerView.setOnSpinnerItemSelectedListener<T> { _, _, newPosition, newItem ->
            onSpinnerItemSelected(newPosition, newItem)
        }
    }
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        ConstraintLayout {
            val (spinner, spinnerText, iconUp, iconDown) = createRefs()
            AndroidView(
                factory = { spinnerView },
                modifier = modifier.constrainAs(spinner) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                update(it)
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_up_arrow),
                contentDescription = "Up arrow",
                modifier = Modifier
                    .constrainAs(iconUp) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .padding(8.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "Down arrow",
                modifier = Modifier
                    .constrainAs(iconDown) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(8.dp)
            )


            with(properties) {
                Text(
                    AnnotatedString(text),
                    Modifier.constrainAs(spinnerText) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        centerVerticallyTo(parent)
                    },
                    color,
                    fontSize,
                    fontStyle,
                    fontWeight,
                    fontFamily,
                    letterSpacing,
                    textDecoration,
                    textAlign,
                    lineHeight,
                    overflow,
                    softWrap,
                    maxLines,
                    style = style,
                    onTextLayout = onTextLayout
                )
            }
        }
    }

}




private fun Context.dp2Px(dp: Dp): Int {
    val scale = resources.displayMetrics.density
    return (dp.value * scale).toInt()
}

private fun TextAlign.toGravity(): Int {
    return when (this) {
        TextAlign.Center -> Gravity.CENTER
        TextAlign.Start, TextAlign.Left -> Gravity.START
        TextAlign.End, TextAlign.Right -> Gravity.END
        TextAlign.Justify -> Gravity.NO_GRAVITY
        else -> throw IllegalArgumentException("Wrong type of the gravity: $this")
    }
}
