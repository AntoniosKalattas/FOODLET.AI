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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Settings


@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    var showSettings by remember { mutableStateOf(false) }

    if (showSettings) {
        SettingsPage(modifier = modifier, viewModel = viewModel)
    } else {
        HomePage(
            modifier = modifier,
            viewModel = viewModel,
            onSettingsClick = { showSettings = true }
        )
    }
}


@Composable
fun HomePage(modifier: Modifier = Modifier, viewModel: MainViewModel, onSettingsClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title: FOODLET.AI
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(48.dp)) // Placeholder for symmetry

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FOODLET",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = ".AI",
                    color = Color.Green,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            GearButton(onClick = onSettingsClick)
        }

        // Calories and Macros Section
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Calories Row
            Row(modifier = Modifier.padding(5.dp)) {
                val calories by viewModel.consumedCaloriers.collectAsState()
                val totalCalorier = viewModel.totalCalories
                Text(
                    text = "Calories:",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = calories.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
                Text(
                    text = "/$totalCalorier",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "MACROS",
                modifier = Modifier.padding(5.dp),
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(3.dp))

            val protein by viewModel.consumedProtein.collectAsState()
            Text(
                text = "Protein: ${protein.toString()}/${viewModel.totalProtein}",
                modifier = Modifier.padding(5.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            val carbs by viewModel.consumedCarbs.collectAsState()
            Text(
                text = "Carbs: ${carbs.toString()}/${viewModel.totalCarbs}",
                modifier = Modifier.padding(5.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            val fat by viewModel.consumedFat.collectAsState()
            Text(
                text = "Fat: ${fat.toString()}/${viewModel.totalFat}",
                modifier = Modifier.padding(5.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // AI Answer Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f) // This takes available space but doesn't expand beyond
        ) {
            Text(
                text = "AI Answer",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            val godAnswer by viewModel.answer.collectAsState()
            Text(
                text = godAnswer.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFF23663A))
                    .border(5.dp, Color.Black)
                    .padding(8.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 10, // Limit the number of lines to prevent excessive expansion
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(
                    onClick = { viewModel.pushLastFood()},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        disabledContainerColor = Color.Gray
                    ),
                    enabled = viewModel.lastFood != null
                ) {
                    Text(text = "Correct")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { viewModel.rejectLastFood() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        disabledContainerColor = Color.Gray
                    ),
                    enabled = viewModel.lastFood != null
                ) {
                    Text(text = "Wrong")
                }
            }
        }

        // Text Input Section - Fixed at bottom
        var foodDescription by remember { mutableStateOf("") }
        TextField(
            value = foodDescription,
            onValueChange = { newFoodDescription -> foodDescription = newFoodDescription },
            label = { Text("Insert Food Description") },
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
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                focusedTrailingIconColor = Color.Gray,
                unfocusedTrailingIconColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun GearButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            tint = Color.Gray
        )
    }
}


@Composable
fun SettingsPage(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Settings",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text="Calories Goal: ${viewModel.totalCalories}",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Protein Goal: ${viewModel.totalProtein}",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Carbs Goal: ${viewModel.totalCarbs}",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Fat Goal: ${viewModel.totalFat}",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}