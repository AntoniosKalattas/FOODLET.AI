package com.example.foodletai // Your package name will be here

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodletai.View.HomePage
import com.example.foodletai.ui.theme.FOODLETAITheme // Your theme name will be here

import com.example.foodletai.ViewModel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel = MainViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        enableEdgeToEdge();

        setContent {
            FOODLETAITheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF191919)
                ) {
                    HomePage(modifier = Modifier.fillMaxSize(),viewModel = viewModel )
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