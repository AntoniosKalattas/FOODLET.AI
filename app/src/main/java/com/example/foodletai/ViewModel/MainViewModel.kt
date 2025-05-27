package com.example.foodletai.ViewModel

import com.example.foodletai.Model.Model
import com.example.foodletai.Model.userData
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.*
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel


data class LastFood(
    val weight: String,
    val calories: String,
    var protein: String,
    val carbs: String,
    val fat: String
)

class MainViewModel:ViewModel() {
    private val model = Model();
    private val userData = userData();

    //AI answer
    private val _godAnswer = MutableStateFlow("ChatGPT-3.5")
    val answer: StateFlow<String> = _godAnswer

    //calories
    val totalCalories = userData.getTotalCalories()
    val _consumedCaloriers = MutableStateFlow(userData.getConsumedCaloriers())
    val consumedCaloriers:StateFlow<Int> = _consumedCaloriers

    // Macros
    val totalFat =     userData.getFat()
    val totalCarbs =   userData.getCarbs()
    val totalProtein = userData.getProtein()
    val totalFiber =   userData.getFiber()
    val totalSugar =   userData.getSugar()
    private val _consumedFat =     MutableStateFlow(userData.getConsumedFat())
    private val _consumedProtein = MutableStateFlow(userData.getConsumedProtein())
    private val _consumedFiber =   MutableStateFlow(userData.getConsumedFiber())
    private val _consumedSugar =   MutableStateFlow(userData.getConsumedSugar())
    private val _consumedCarbs =   MutableStateFlow(userData.getConsumedCarbs())
    val consumedProtein:StateFlow<Int> =  _consumedProtein
    val consumedFat:StateFlow<Int> =      _consumedFat
    val consumedCarbs:StateFlow<Int> =    _consumedCarbs
    val consumedFiber:StateFlow<Int> =    _consumedFiber
    val consumedSugar:StateFlow<Int> =    _consumedSugar
    var lastFood: LastFood? = null



    fun addFoodDescription(foodDescription: String) {
        lastFood=null
        if(model.isFoodRelated(foodDescription)) {
            model.askGod(viewModelScope, foodDescription) { result ->
                try {
                    Log.d("API", "Response: $result")

                    // Parse as JsonElement first
                    val jsonElement = Json.parseToJsonElement(result)
                    val jsonObject = jsonElement.jsonObject

                    // Extract values dynamically
                    val weight = extractValue(jsonObject, "weight")
                    val calories = extractValue(jsonObject, "calories")
                    val protein = extractValue(jsonObject, "protein")
                    val carbs = extractValue(jsonObject, "carbs")
                    val fat = extractValue(jsonObject, "fat")

                    // Format the response
                    val formattedAnswer = buildString {
                        appendLine("weight: $weight")
                        appendLine("calories: $calories")
                        appendLine("protein: $protein")
                        appendLine("carbs: $carbs")
                        appendLine("fat: $fat")
                    }

                    _godAnswer.value = formattedAnswer.trim()
                    lastFood = LastFood(weight, calories, protein, carbs, fat)

                } catch (e: Exception) {
                    Log.e("API", "Error parsing JSON: ${e.message}")
                    _godAnswer.value = "Error parsing response"
                }
            }
        }
        else{
            _godAnswer.value = "Please insert food description!"
        }
    }
    // Helper function to extract values from nested or flat JSON
    private fun extractValue(jsonObject: JsonObject, key: String): String {
        // First, try to find the key at the root level
        jsonObject[key]?.let { value ->
            return when (value) {
                is JsonPrimitive -> value.content
                else -> value.toString()
            }
        }

        // If not found at root, search in nested objects
        for ((_, nestedValue) in jsonObject) {
            if (nestedValue is JsonObject) {
                nestedValue[key]?.let { value ->
                    return when (value) {
                        is JsonPrimitive -> value.content
                        else -> value.toString()
                    }
                }
            }
        }

        return "N/A"
    }

    fun pushLastFood(){
        lastFood?.let { food ->
            userData.setConsumedCaloriers(consumedCaloriers.value + food.calories.replace("g", "").toInt())
            userData.setConsumedFat(consumedFat.value + food.fat.replace("g", "").toInt())
            userData.setConsumedCarbs(consumedCarbs.value + food.carbs.replace("g", "").toInt())
            userData.setConsumedProtein(consumedProtein.value + food.protein.replace("g", "").toInt())

            //update state flow
            _consumedCaloriers.value += food.calories.replace("g", "").toInt()
            _consumedFat.value += food.fat.replace("g", "").toInt()
            _consumedCarbs.value += food.carbs.replace("g", "").toInt()
            _consumedProtein.value += food.protein.replace("g", "").toInt()
        }
        lastFood = null
    }

    fun rejectLastFood(){
        lastFood = null
    }

    fun storeData(){
        val sharedPref = getSharedPreferences("foodlet_prefs", MODE_PRIVATE)
        sharedPref.edit().putInt("calorie_goal", 2000).apply()

    }
    fun loadData(){

    }

    fun addConsumedCaloriers(caloriers:Int){
        userData.setConsumedCaloriers(caloriers)
        _consumedCaloriers.value+=caloriers
    }

}