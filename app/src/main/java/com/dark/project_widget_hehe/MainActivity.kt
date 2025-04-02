package com.dark.project_widget_hehe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dark.project_widget_hehe.ui.theme.ProjectWidgetHeheTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWidgetHeheTheme {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        "Nice Widget", Modifier.align(Alignment.Center), fontFamily = FontFamily(
                            Font(R.font.sansita_bold)
                        ),
                        fontSize = 34.sp
                    )
                }
            }
        }
    }
}