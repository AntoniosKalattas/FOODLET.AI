package com.example.foodletai.myModel

import android.os.Build
import com.example.foodletai.api.ApiControl
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class Model{
//    private val prompts:String = "Analyze this food description and return ONLY valid JSON in this exact format:\n{\n  \"weight\": \"200g\","
    private var god:ApiControl = ApiControl()
    fun askGod(scope: CoroutineScope, question: String, onResult: (String) -> Unit){
        scope.launch {
            val result = god.question(userMessage ="Give me in json format the weight, the calories,protein, carbs, and fat of this food: $question")
            Log.d("API", "Response: $result")
            onResult(result)
        }
    }

    fun isFoodRelated(sentence: String): Boolean {
        val keywords = listOf(
            "food", "eat", "meal", "snack", "lunch", "dinner", "breakfast",
            "calorie", "protein", "carbs", "fat", "sugar", "cook", "recipe",
            "dish", "ingredient", "nutrition", "diet", "burger", "pizza", "chicken",
            "rice", "salad", "fries", "steak", "egg", "fruit", "vegetable", "bread"
        )
        val lower = sentence.lowercase()
        return keywords.any { lower.contains(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkDay(userData: UserData):Boolean{
        val currentDate = LocalDate.now()
        val dayOfWeek = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        Log.d("Model", "last checked day: ${userData.getLastDay()}")
        Log.d("Model", "Checking day: $dayOfWeek")
        if(!dayOfWeek.equals(userData.getLastDay())){
            userData.resetDayData()
            userData.setLastDay(dayOfWeek)
            userData.saveState()
            return false
        }
        return true
    }
}

