package com.example.foodletai // Your package name will be here

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodletai.ui.theme.FOODLETAITheme
import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.foodletai.view.MainScreen
import com.example.foodletai.myviewmodel.MainViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FOODLETAITheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD9D9D9)
                ) {
                    MainScreen(modifier = Modifier.fillMaxSize(),viewModel = viewModel )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FOODLETAITheme {
        Greeting("Android")
    }
}