package com.example.alarmqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.alarmqr.ui.MainView
import com.example.alarmqr.ui.theme.AlarmQRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlarmQRTheme {
                AlarmQRApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun AlarmQRApp() {
    MainView()
}
