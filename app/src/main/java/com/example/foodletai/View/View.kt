package com.example.foodletai.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodletai.R
import com.example.foodletai.ViewModel.MainViewModel

val titleFont = FontFamily(Font(R.font.jersey25_regular))
val mainFont = FontFamily(Font(R.font.jersey20_regular))

val textBoxFont = FontFamily(Font(R.font.roboto_semicondensed_regular))

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    var showSettings by remember { mutableStateOf(false) }

    if (showSettings) {
        SettingsPage(modifier = modifier, viewModel = viewModel, onBack = { showSettings = false })
    } else {
        HomePage(
                modifier = modifier,
                viewModel = viewModel,
                onSettingsClick = { showSettings = true }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(
<<<<<<< Updated upstream
        modifier: Modifier = Modifier,
        titleFont: FontFamily = FontFamily.Default,
        mainFont: FontFamily = FontFamily.Default,
        viewModel: MainViewModel,
        onSettingsClick: () -> Unit
) {
    Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title: FOOD LET.AI
        Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
=======
    modifier: Modifier = Modifier,
    titleFont: FontFamily = FontFamily.Default,
    mainFont: FontFamily = FontFamily.Default,
    viewModel: MainViewModel,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title: FOOD LET.AI
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
>>>>>>> Stashed changes
        ) {
            Spacer(modifier = Modifier.width(48.dp)) // Placeholder for symmetry

            Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
<<<<<<< Updated upstream
                        text = "FOODLET",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = titleFont // Use the custom font family
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                        text = ".AI",
                        color = Color.Green,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = titleFont
=======
                    text = "FOODLET",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = titleFont // Use the custom font family
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = ".AI",
                    color = Color.Green,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = titleFont
>>>>>>> Stashed changes
                )
            }

            GearButton(onClick = onSettingsClick)
        }
        Row(
<<<<<<< Updated upstream
                modifier =
                        Modifier.fillMaxWidth()
                                .height(IntrinsicSize.Min), // Ensures columns match height
                horizontalArrangement = Arrangement.Center
        ) {
            // Calories and Macros Section
            Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f).fillMaxHeight()
=======
            modifier =
                Modifier.fillMaxWidth()
                    .height(IntrinsicSize.Min), // Ensures columns match height
            horizontalArrangement = Arrangement.Center
        ) {
            // Calories and Macros Section
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f).fillMaxHeight()
>>>>>>> Stashed changes
            ) {
                // Calories Row
                Row(modifier = Modifier.padding(5.dp)) {
                    val calories by viewModel.consumedCalories.collectAsState()
                    val totalCalories = viewModel.totalCalories.collectAsState().value
                    Text(
<<<<<<< Updated upstream
                            text = "Calories: ${calories}/${totalCalories}",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left,
                            fontFamily = mainFont
=======
                        text = "Calories: ${calories}/${totalCalories}",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        fontFamily = mainFont
>>>>>>> Stashed changes
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
<<<<<<< Updated upstream
                        text = "MACROS",
                        modifier = Modifier.padding(5.dp),
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = mainFont
=======
                    text = "MACROS",
                    modifier = Modifier.padding(5.dp),
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = mainFont
>>>>>>> Stashed changes
                )
                Spacer(modifier = Modifier.height(3.dp))

                val protein by viewModel.consumedProtein.collectAsState()
                Text(
<<<<<<< Updated upstream
                        text =
                                "Protein:${protein}/${viewModel.totalProtein.collectAsState().value}",
                        modifier = Modifier.padding(5.dp),
                        color = Color(0xFF6C81A7),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = mainFont
=======
                    text =
                        "Protein:${protein}/${viewModel.totalProtein.collectAsState().value}",
                    modifier = Modifier.padding(5.dp),
                    color = Color(0xFF6C81A7),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    fontFamily = mainFont
>>>>>>> Stashed changes
                )

                val carbs by viewModel.consumedCarbs.collectAsState()
                Text(
<<<<<<< Updated upstream
                        text = "Carbs:${carbs}/${viewModel.totalCarbs.collectAsState().value}",
                        modifier = Modifier.padding(5.dp),
                        color = Color(0xFFA8F7FA),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = mainFont
=======
                    text = "Carbs:${carbs}/${viewModel.totalCarbs.collectAsState().value}",
                    modifier = Modifier.padding(5.dp),
                    color = Color(0xFFA8F7FA),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    fontFamily = mainFont
>>>>>>> Stashed changes
                )

                val fat by viewModel.consumedFat.collectAsState()
                Text(
<<<<<<< Updated upstream
                        text = "Fat:${fat}/${viewModel.totalFat.collectAsState().value}",
                        modifier = Modifier.padding(5.dp),
                        color = Color(0xFFABA8FA),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = mainFont
=======
                    text = "Fat:${fat}/${viewModel.totalFat.collectAsState().value}",
                    modifier = Modifier.padding(5.dp),
                    color = Color(0xFFABA8FA),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    fontFamily = mainFont
>>>>>>> Stashed changes
                )
            }

            // Vertical Progress Bar for Macros
            val consumedProtein = viewModel.consumedProtein.collectAsState().value.toFloat()
            val totalProtein =
<<<<<<< Updated upstream
                    viewModel.totalProtein.collectAsState().value.toFloat().coerceAtLeast(1f)
            Box(
                    modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                    contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                        progress = consumedProtein / totalProtein,
                        modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                        progressColor = Color(0xFF6C81A7)
=======
                viewModel.totalProtein.collectAsState().value.toFloat().coerceAtLeast(1f)
            Box(
                modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                    progress = consumedProtein / totalProtein,
                    modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                    progressColor = Color(0xFF6C81A7)
>>>>>>> Stashed changes
                )
            }

            // Vertical Progress Bar for Carbs
            val consumedCarbs = viewModel.consumedCarbs.collectAsState().value.toFloat()
            val totalCarbs = viewModel.totalCarbs.collectAsState().value.toFloat().coerceAtLeast(1f)
            Box(
<<<<<<< Updated upstream
                    modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                    contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                        progress = consumedCarbs / totalCarbs,
                        modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                        progressColor = Color(0xFFA8F7FA)
=======
                modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                    progress = consumedCarbs / totalCarbs,
                    modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                    progressColor = Color(0xFFA8F7FA)
>>>>>>> Stashed changes
                )
            }
            // Vertical Progress Bar for Fat
            val consumedFat = viewModel.consumedFat.collectAsState().value.toFloat()
            val totalFat = viewModel.totalFat.collectAsState().value.toFloat().coerceAtLeast(1f)
            Box(
<<<<<<< Updated upstream
                    modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                    contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                        progress = consumedFat / totalFat,
                        modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                        progressColor = Color(0xFFABA8FA)
=======
                modifier = Modifier.padding(start = 16.dp).fillMaxHeight().width(32.dp),
                contentAlignment = Alignment.Center
            ) {
                VerticalProgressBar(
                    progress = consumedFat / totalFat,
                    modifier = Modifier.fillMaxHeight(0.8f).width(50.dp),
                    progressColor = Color(0xFFABA8FA)
>>>>>>> Stashed changes
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // AI Answer Section
        Column(
<<<<<<< Updated upstream
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                        Modifier.weight(1f) // This takes available space but doesn't expand beyond
        ) {
            Text(
                    text = "AI Response",
                    color = Color.White,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
            )

            val godAnswer by viewModel.answer.collectAsState()
            Text(
                    text = godAnswer.toString(),
                    modifier =
                            Modifier.fillMaxWidth()
                                    .padding(16.dp)
                                    .background(Color(0xFF202020))
                                    .border(1.dp, Color.Gray)
                                    .padding(8.dp),
=======
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier.weight(1f) // This takes available space but doesn't expand beyond
        ) {
            Text(
                text = "AI Response",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            val godAnswer by viewModel.answer.collectAsState()
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                    .background(Color(0xFFAEAEAE))
                    .border(
                        1.dp,
                        Color.Transparent,
                        androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                    )
            ) {
                Text(
                    text = godAnswer.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(10.dp))
                        .background(Color(0xFFAEAEAE))
                        .border(
                            1.dp,
                            Color.Transparent,
                            androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp),
>>>>>>> Stashed changes
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
<<<<<<< Updated upstream
                    maxLines = 10, // Limit the number of lines to prevent excessive expansion
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
=======
                    maxLines = 10,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
>>>>>>> Stashed changes

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(
<<<<<<< Updated upstream
                        onClick = { viewModel.pushLastFood() },
                        colors =
                                ButtonDefaults.buttonColors(
                                        containerColor = Color.Green,
                                        disabledContainerColor = Color.Gray
                                ),
                        enabled = viewModel.lastFood != null
                ) { Text(text = "Correct") }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                        onClick = { viewModel.rejectLastFood() },
                        colors =
                                ButtonDefaults.buttonColors(
                                        containerColor = Color.Red,
                                        disabledContainerColor = Color.Gray
                                ),
                        enabled = viewModel.lastFood != null
                ) { Text(text = "Wrong") }
=======
                    onClick = { viewModel.pushLastFood() },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Green,
                            disabledContainerColor = Color.Gray
                        ),
                    enabled = viewModel.lastFood != null
                ) { Text(text = "Push", modifier = Modifier.fontFamily) }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { viewModel.rejectLastFood() },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            disabledContainerColor = Color.Gray
                        ),
                    enabled = viewModel.lastFood != null
                ) { Text(text = "Re Try") }
>>>>>>> Stashed changes
            }
        }

        // Text Input Section - Fixed at bottom
        var foodDescription by remember { mutableStateOf("") }
        TextField(
<<<<<<< Updated upstream
                value = foodDescription,
                onValueChange = { newFoodDescription -> foodDescription = newFoodDescription },
                label = { Text("Insert Food Description") },
                placeholder = { Text("Write Food Description") },
                trailingIcon = {
                    IconButton(onClick = { viewModel.addFoodDescription(foodDescription) }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = "Send")
                    }
                },
                colors =
                        TextFieldDefaults.colors(
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
=======
            value = foodDescription,
            onValueChange = { newFoodDescription -> foodDescription = newFoodDescription },
            label = { Text("Insert Food Description") },
            placeholder = { Text("Write Food Description") },
            trailingIcon = {
                IconButton(onClick = { viewModel.addFoodDescription(foodDescription) }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Send")
                }
            },
            colors =
                TextFieldDefaults.colors(
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
>>>>>>> Stashed changes
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun GearButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color.Gray
        )
    }
}

@Composable
fun SettingsPage(modifier: Modifier = Modifier, viewModel: MainViewModel, onBack: () -> Unit) {
    Column(
<<<<<<< Updated upstream
            modifier = modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
=======
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
>>>>>>> Stashed changes
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            BackButton(onClick = onBack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                    text = "Settings",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
            )
        }
        // Calories and Macros Settings

        // Calories Goal
        EditableSettingRow(
<<<<<<< Updated upstream
                label = "Calories Goal",
                value = viewModel.totalCalories.collectAsState().value.toString(),
                onValueChange = { viewModel.setTotalCalories(it.toInt()) }
=======
            label = "Calories Goal",
            value = viewModel.totalCalories.collectAsState().value.toString(),
            onValueChange = { viewModel.setTotalCalories(it.toInt()) }
>>>>>>> Stashed changes
        )

        // Protein Goal
        EditableSettingRow(
                label = "Protein Goal",
                value = viewModel.totalProtein.collectAsState().value.toString(),
                onValueChange = { viewModel.setTotalProtein(it.toInt()) }
        )

        // Carbs Goal
        EditableSettingRow(
<<<<<<< Updated upstream
                label = "Carbs Goal",
                value = viewModel.totalCarbs.collectAsState().value.toString(),
                onValueChange = { viewModel.setTotalCarbs(it.toInt()) }
=======
            label = "Carbs Goal",
            value = viewModel.totalCarbs.collectAsState().value.toString(),
            onValueChange = { viewModel.setTotalCarbs(it.toInt()) }
>>>>>>> Stashed changes
        )

        // Fat Goal
        EditableSettingRow(
<<<<<<< Updated upstream
                label = "Fat Goal",
                value = viewModel.totalFat.collectAsState().value.toString(),
                onValueChange = { viewModel.setTotalFat(it.toInt()) }
=======
            label = "Fat Goal",
            value = viewModel.totalFat.collectAsState().value.toString(),
            onValueChange = { viewModel.setTotalFat(it.toInt()) }
>>>>>>> Stashed changes
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Back Button
        viewModel.saveUserData() // Save settings when leaving the page
    }
}

@Composable
fun PenButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit", tint = Color.Gray)
    }
}

@Composable
fun EditableSettingRow(label: String, value: String, onValueChange: (String) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(value) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (isEditing) {
            TextField(
                    value = text,
                    onValueChange = { text = it },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
            )
            Button(
<<<<<<< Updated upstream
                    onClick = {
                        onValueChange(text)
                        isEditing = false
                    }
=======
                onClick = {
                    onValueChange(text)
                    isEditing = false
                }
>>>>>>> Stashed changes
            ) { Text("Save") }
        } else {
            Text(
                    text = "$label: $value",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
            )
            PenButton(onClick = { isEditing = true })
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
        )
    }
}

@Composable
fun VerticalProgressBar(
<<<<<<< Updated upstream
        progress: Float,
        modifier: Modifier = Modifier,
        backgroundColor: Color = Color(0xFFAEAEAE),
        progressColor: Color = Color.Blue
) {
    val shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    Box(
            modifier =
                    modifier.clip(shape)
                            .background(backgroundColor, shape)
                            .border(2.dp, Color.Transparent, shape)
    ) {
        Box(
                modifier =
                        Modifier.fillMaxWidth()
                                .width(500.dp)
                                .fillMaxHeight(progress)
                                .align(Alignment.BottomCenter)
                                .background(progressColor, shape)
=======
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFAEAEAE),
    progressColor: Color = Color.Blue
) {
    val shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
    Box(
        modifier =
            modifier.clip(shape)
                .background(backgroundColor, shape)
                .border(2.dp, Color.Transparent, shape)
    ) {
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .width(500.dp)
                    .fillMaxHeight(progress)
                    .align(Alignment.BottomCenter)
                    .background(progressColor, shape)
>>>>>>> Stashed changes
        )
    }
}
