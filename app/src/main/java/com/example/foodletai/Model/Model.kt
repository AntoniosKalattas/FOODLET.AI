package com.example.foodletai.Model

import com.example.foodletai.API.ApiControl
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Model() {
//    private val prompts:String = "Analyze this food description and return ONLY valid JSON in this exact format:\n{\n  \"weight\": \"200g\","
    private var god:ApiControl = ApiControl();
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

}