package com.example.foodletai.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodletai.ViewModel.MainViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf // Import for mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.collectAsState

@Composable
fun HomePage(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    // Declare the state at the top level of the composable
    val usernameState = rememberTextFieldState(initialText = "Username")


    Column(modifier = modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ai Assistant Answer",modifier = Modifier, color = Color.White, textAlign = TextAlign.Center)

        val godAnswer by viewModel.answer.collectAsState()
        Text(
            text = godAnswer,
            modifier = Modifier
                .padding(16.dp) // Outer padding: space around the entire box (border included)
                .background(Color(0xFF23663A)) // Background color for the content area
                .border(5.dp, Color.Black) // Draws the border
                .padding(8.dp), // Inner padding: space between the border and the text
            color = Color.White,
            textAlign = TextAlign.Center
        )


    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var foodDescription by remember { mutableStateOf("") }
        TextField(
            value = foodDescription,
            onValueChange = { newFoodDescription -> foodDescription = newFoodDescription },
            label = { Text("Food Description") },
            placeholder = { Text("Write Food Description") },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.addFoodDescription(foodDescription)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Send"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.LightGray,
                focusedTrailingIconColor = Color.Gray,
                unfocusedTrailingIconColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier.padding(70.dp) // Add some padding from screen edges
        )
    }



}