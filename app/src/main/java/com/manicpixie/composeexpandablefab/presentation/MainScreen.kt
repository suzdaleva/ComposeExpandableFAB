package com.manicpixie.composeexpandablefab.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        ExpandableFAB(modifier = Modifier
            .padding(bottom = 30.dp, end = 30.dp)
            .align(Alignment.BottomEnd),
            onAddNewSession = {
                Toast.makeText(
                    context, "Item added",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onDeleteAllSessions = {
                Toast.makeText(
                    context, "Item deleted",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}