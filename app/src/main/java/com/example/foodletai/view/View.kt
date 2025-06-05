package com.example.foodletai.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.foundation.layout.weight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodletai.R
import com.example.foodletai.viewmodel.MainViewModel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CircularProgressIndicator

val titleFont = FontFamily(Font(R.font.jersey25_regular))
val mainFont = FontFamily(Font(R.font.jersey20_regular))

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
        ) {
            Spacer(modifier = Modifier.width(40.dp)) // Placeholder for symmetry

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FOODLET.AI",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = titleFont // Use the custom font family
                )
            }

            GearButton(onClick = onSettingsClick)
        }


        // Calories and Macros Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp), // This constrains the total height
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
                    .fillMaxHeight() // Fill the 180.dp height
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Calories Row
                    val calories by viewModel.consumedCalories.collectAsState()
                    val totalCalories by viewModel.totalCalories.collectAsState()
                    Text(
                        text = "Calories: ${calories}/${totalCalories}",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        fontFamily = mainFont
                    )
                    Spacer(modifier = Modifier.width(85.dp)) // Pushes the progress bar to the right
                    val totalCaloriesValue by viewModel.totalCalories.collectAsState()
                    val consumedCaloriesValue by viewModel.consumedCalories.collectAsState()
                    // Circular Progress Bar for Calories
                    CircularProgressBar(size = 60,progress = consumedCaloriesValue.toFloat() / totalCaloriesValue.toFloat())
                }
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "MACROS",
                    modifier = Modifier.padding(5.dp),
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = mainFont
                )
                Spacer(modifier = Modifier.height(3.dp))



                // Macros Section
                Row(
                    modifier = Modifier.fillMaxHeight(), // Fill remaining height in the column
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        val protein by viewModel.consumedProtein.collectAsState()
                        val totalProteinValue by viewModel.totalProtein.collectAsState()
                        Text(
                            text = "Protein: ${protein}g/${totalProteinValue}g",
                            modifier = Modifier.padding(5.dp),
                            color = Color(0xFF6C81A7),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp, // Slightly smaller to fit better
                            fontFamily = mainFont
                        )

                        val carbs by viewModel.consumedCarbs.collectAsState()
                        val totalCarbsValue by viewModel.totalCarbs.collectAsState()
                        Text(
                            text = "Carbs: ${carbs}g/${totalCarbsValue}g",
                            modifier = Modifier.padding(5.dp),
                            color = Color(0xFFA8F7FA),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp, // Slightly smaller to fit better
                            fontFamily = mainFont
                        )

                        val fat by viewModel.consumedFat.collectAsState()
                        val totalFatValue by viewModel.totalFat.collectAsState()
                        Text(
                            text = "Fat: ${fat}g/${totalFatValue}g",
                            modifier = Modifier.padding(5.dp),
                            color = Color(0xFFABA8FA),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp, // Slightly smaller to fit better
                            fontFamily = mainFont
                        )
                    }

                    // Progress Bars Section
                    Row(
                        modifier = Modifier
                            .height(120.dp) // Fixed height for progress bars
                            .padding(start = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // Protein Progress Bar
                        val consumedProtein by viewModel.consumedProtein.collectAsState()
                        val totalProtein by viewModel.totalProtein.collectAsState()
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(120.dp), // Fixed height instead of fillMaxHeight
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            VerticalProgressBar(
                                progress = if (totalProtein > 0) (consumedProtein.toFloat() / totalProtein.toFloat()).coerceIn(0f, 1f) else 0f,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(120.dp),
                                progressColor = Color(0xFF6C81A7),
                                backgroundColor = Color(0xFFD0D7E3)
                            )
                        }

                        // Carbs Progress Bar
                        val consumedCarbs by viewModel.consumedCarbs.collectAsState()
                        val totalCarbs by viewModel.totalCarbs.collectAsState()
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(120.dp), // Fixed height instead of fillMaxHeight
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            VerticalProgressBar(
                                progress = if (totalCarbs > 0) (consumedCarbs.toFloat() / totalCarbs.toFloat()).coerceIn(0f, 1f) else 0f,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(120.dp),
                                progressColor = Color(0xFFA8F7FA),
                                backgroundColor = Color(0xFFF2FEFE)
                            )
                        }

                        // Fat Progress Bar
                        val consumedFat by viewModel.consumedFat.collectAsState()
                        val totalFat by viewModel.totalFat.collectAsState()
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(120.dp), // Fixed height instead of fillMaxHeight
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            VerticalProgressBar(
                                progress = if (totalFat > 0) (consumedFat.toFloat() / totalFat.toFloat()).coerceIn(0f, 1f) else 0f,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(120.dp),
                                progressColor = Color(0xFFABA8FA),
                                backgroundColor = Color(0xFFF2F2FE)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        AiResponseSection(viewModel)

        TextInputSection(viewModel)

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TextInputSection(viewModel: MainViewModel){
    var foodDescription by remember { mutableStateOf("") }
    val shape = RoundedCornerShape(10.dp)
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(130.dp)
            .padding(horizontal = 10.dp, vertical = 30.dp)
            .clip(shape)
            .background(Color.White, shape = shape)
            .border(2.dp, Color.White, shape) // Rounded border
    ) {
        TextField(
            value = foodDescription,
            onValueChange = { newFoodDescription -> foodDescription = newFoodDescription },
            label = { Text("Describe food") },
            placeholder = { Text("Describe food") },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.addFoodDescription(foodDescription)
                    viewModel._loadingState.value=true
                    foodDescription = "" // Clear input after submission
                }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Send")
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                unfocusedTrailingIconColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AiResponseSection(viewModel: MainViewModel){
    // AI Answer Section
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //modifier = Modifier.weight(1f) // This takes available space but doesn't expand beyond
    ) {
        Text(
            text = "AI Response",
            color = Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontFamily = mainFont
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(150.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFAEAEAE), shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.Transparent, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            val loadingState by viewModel.loadingState.collectAsState()
            if(loadingState) {
                CircularProgressIndicator(color = Color.White)
            }
            else{
                val godAnswer by viewModel.answer.collectAsState()
                Text(
                    text = godAnswer,
                    fontFamily = mainFont,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 10, // Limit the number of lines to prevent excessive expansion
                    overflow = TextOverflow.Ellipsis
            )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Row {
            Button(
                onClick = { viewModel.pushLastFood();},
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA8F7FA),
                        disabledContainerColor = Color.Gray
                    ),
                enabled = viewModel.lastFood != null
            ) { Text(text = "Push", fontFamily = mainFont) }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = { viewModel.reTry(); viewModel._loadingState.value=true },
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C81A7),
                        disabledContainerColor = Color.Gray
                    ),
                enabled = viewModel.lastFood != null
            ) { Text(text = "Re Try", fontFamily = mainFont) }
        }
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
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            BackButton(onClick = onBack)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Settings",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = titleFont
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Calories and Macros Settings
        // Calories Goal
        EditableSettingRow(
            label = "Calories Goal",
            value = viewModel.totalCalories.collectAsState().value.toString(),
            onValueChange = { newValue ->
                try {
                    viewModel.setTotalCalories(newValue.toInt())
                } catch (e: NumberFormatException) {
                    // Handle invalid input gracefully
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Protein Goal
        EditableSettingRow(
            label = "Protein Goal (g)",
            value = viewModel.totalProtein.collectAsState().value.toString(),
            onValueChange = { newValue ->
                try {
                    viewModel.setTotalProtein(newValue.toInt())
                } catch (e: NumberFormatException) {
                    // Handle invalid input gracefully
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Carbs Goal
        EditableSettingRow(
            label = "Carbs Goal (g)",
            value = viewModel.totalCarbs.collectAsState().value.toString(),
            onValueChange = { newValue ->
                try {
                    viewModel.setTotalCarbs(newValue.toInt())
                } catch (e: NumberFormatException) {
                    // Handle invalid input gracefully
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Fat Goal
        EditableSettingRow(
            label = "Fat Goal (g)",
            value = viewModel.totalFat.collectAsState().value.toString(),
            onValueChange = { newValue ->
                try {
                    viewModel.setTotalFat(newValue.toInt())
                } catch (e: NumberFormatException) {
                    // Handle invalid input gracefully
                }
            }
        )

//        Spacer(modifier = Modifier.height(15.dp))
//
//        val aiModels = listOf("GPT-3.5", "GPT-4", "Gemini", "Claude")
//        var selectedModel by remember { mutableStateOf(aiModels.first()) }
//
//        DropdownSettingRow(
//            label = "AI Model",
//            options = aiModels,
//            selectedOption = selectedModel,
//            onOptionSelected = { selectedModel = it }
//        )

        Spacer(modifier = Modifier.height(20.dp))
        // Save settings when leaving the page
        viewModel.saveUserData()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSettingRow(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
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

    // Update text when value changes externally
    if (!isEditing && text != value) {
        text = value
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        if (isEditing) {
            TextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    onValueChange(text)
                    isEditing = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA8F7FA)
                )
            ) {
                Text("Save", color = Color.Black, fontFamily = mainFont)
            }
        } else {
            Text(
                text = "$label: $value",
                color = Color.White,
                fontSize = 24.sp, // Slightly smaller for better fit
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                fontFamily = mainFont
            )
            PenButton(onClick = {
                text = value // Ensure text is up to date when starting edit
                isEditing = true
            })
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
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFAEAEAE),
    progressColor: Color = Color.Blue
) {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier =
            modifier.clip(shape)
                .background(backgroundColor, shape)
                .border(2.dp, Color.Transparent, shape)
    ) {
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .fillMaxHeight(progress.coerceIn(0f, 1f)) // Ensure progress is between 0 and 1
                    .align(Alignment.BottomCenter)
                    .background(progressColor, shape)
        )
    }
}


@Composable
fun CircularProgressBar(
    progress: Float, // 0.0 to 1.0
    modifier: Modifier = Modifier,
    size: Int = 200,
    strokeWidth: Float = size * 0.15f,
    backgroundColor: Color = Color.LightGray,
    progressColor: Color = Color(0xFFF1FAA8),
    backgroundFillColor: Color = Color.White
) {
    Canvas(modifier = modifier.size(size.dp).aspectRatio(1f)) {
        // Draw background fill circle
        drawCircle(
            color = backgroundFillColor,
            radius = this.size.minDimension / 2f
        )

        // Draw background circle
        drawArc(
            color = backgroundColor,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
        // Draw progress arc
        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = 360f * progress.coerceIn(0f, 1f),
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}