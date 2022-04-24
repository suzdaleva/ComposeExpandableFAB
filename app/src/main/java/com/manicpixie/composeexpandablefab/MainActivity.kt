package com.manicpixie.composeexpandablefab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.manicpixie.composeexpandablefab.presentation.MainScreen
import com.manicpixie.composeexpandablefab.ui.theme.ComposeExpandableFABTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExpandableFABTheme {
              MainScreen()
            }
        }
    }
}
