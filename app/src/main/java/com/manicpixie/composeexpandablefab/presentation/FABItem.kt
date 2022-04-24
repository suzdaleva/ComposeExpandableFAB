package com.manicpixie.composeexpandablefab.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.manicpixie.composeexpandablefab.ui.theme.PrimaryBlack


@Composable
fun FABItem(
    offset: IntOffset = IntOffset(0, 0),
    onClick: () -> Unit,
    size: Dp,
    @DrawableRes resId: Int,
    backgroundColor: Color,
    colorFilter : ColorFilter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .offset {
                offset
            }
            .size(68.dp)
            .clip(CircleShape)
            .noRippleClickable {
                onClick()
            }
            .drawColoredShadow(
                alpha = 0.15f,
                shadowRadius = 5.dp,
                color = PrimaryBlack,
                borderRadius = 34.dp
            )
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            colorFilter = colorFilter,
            modifier = Modifier.size(size),
            painter = painterResource(resId),
            contentDescription = "FAB"
        )
    }
}