package com.manicpixie.composeexpandablefab.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.manicpixie.composeexpandablefab.ui.theme.PrimaryBlack
import kotlin.math.roundToInt
import com.manicpixie.composeexpandablefab.R


@OptIn(ExperimentalAnimationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Composable
fun ExpandableFAB(
    onDeleteAllSessions: () -> Unit,
    onAddNewSession: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isFabClicked = remember { mutableStateOf(false) }
    val animationSpec: AnimationSpec<Float> = spring(
        stiffness = Spring.StiffnessLow,
        dampingRatio = Spring.DampingRatioLowBouncy
    )
    val density = LocalDensity.current.density
    val offsetDeleteY = animateFloatAsState(
        targetValue = if (isFabClicked.value) -250f*density/2.75f else 0f,
        animationSpec = animationSpec
    )
    val offsetAddNewY = animateFloatAsState(
        targetValue = if (isFabClicked.value) -500f*density/2.75f else 0f,
        animationSpec = animationSpec
    )
    val rotationAngle = animateFloatAsState(
        targetValue = if (isFabClicked.value) 90f else 0f,
        animationSpec = animationSpec)
    val plusFabColor = animateColorAsState(
        animationSpec = tween(durationMillis = 200),
        targetValue = if (isFabClicked.value) White else PrimaryBlack
    )
    AnimatedVisibility(
        enter = fadeIn(),
        exit = fadeOut(),
        visible = isFabClicked.value
    ) {
        Box(modifier = Modifier
            .pointerInteropFilter {
                isFabClicked.value = !isFabClicked.value
                false }
            .alpha(0.8f)
            .background(Color.Black)
            .fillMaxSize())
    }
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(0, offsetAddNewY.value.roundToInt())
                }
                .size(68.dp)
                .clip(CircleShape)
                .noRippleClickable {
                    isFabClicked.value = !isFabClicked.value
                    onAddNewSession() }
                .drawColoredShadow(
                    alpha = 0.15f,
                    shadowRadius = 5.dp,
                    color = PrimaryBlack,
                    borderRadius = 34.dp
                )
                .background(White),
            contentAlignment = Alignment.Center) {
            Image(
                colorFilter = ColorFilter.tint(PrimaryBlack),
                modifier = Modifier.size(33.dp),
                painter = painterResource(id = R.drawable.fab_add_new),
                contentDescription = "FAB"
            )
        }
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(0, offsetDeleteY.value.roundToInt())
                }
                .size(68.dp)
                .clip(CircleShape)
                .noRippleClickable {
                    isFabClicked.value = !isFabClicked.value
                    onDeleteAllSessions()
                }
                .drawColoredShadow(
                    alpha = 0.15f,
                    shadowRadius = 5.dp,
                    color = PrimaryBlack,
                    borderRadius = 34.dp
                )
                .background(White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                colorFilter = ColorFilter.tint(PrimaryBlack),
                modifier = Modifier.size(33.dp),
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "FAB"
            )
        }
        Box(
            modifier = Modifier
                .drawColoredShadow(
                    alpha = 0.15f,
                    shadowRadius = 5.dp,
                    color = PrimaryBlack,
                    borderRadius = 34.dp
                )
                .size(68.dp)
                .clip(CircleShape)
                .rotate(rotationAngle.value)
                .noRippleClickable { isFabClicked.value = !isFabClicked.value }
                .background(plusFabColor.value),
            contentAlignment = Alignment.Center) {
            Image(
                colorFilter = if (isFabClicked.value) ColorFilter.tint(PrimaryBlack) else null,
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.fab_plus),
                contentDescription = "FAB"
            )
        }

    }
}