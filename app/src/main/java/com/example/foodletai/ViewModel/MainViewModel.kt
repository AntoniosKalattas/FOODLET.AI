package com.example.foodletai.ViewModel

import com.example.foodletai.Model.Model
import com.example.foodletai.Model.userData
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.*
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel


data class LastFood(
    val weight: String,
    val calories: String,
    var protein: String,
    val carbs: String,
    val fat: String
)

class MainViewModel(application: Application):AndroidViewModel(application = Application()) {
    private val model = Model();
    private val userData = userData(application);

    //AI answer
    private val _godAnswer = MutableStateFlow("ChatGPT-3.5")
    val answer: StateFlow<String> = _godAnswer

    //calories
    private val _totalCalories = MutableStateFlow(userData.getTotalCalories())
    val totalCalories: StateFlow<Int> = _totalCalories

    private val _totalFat = MutableStateFlow(userData.getFat())
    val totalFat: StateFlow<Int> = _totalFat

    private val _totalCarbs = MutableStateFlow(userData.getCarbs())
    val totalCarbs: StateFlow<Int> = _totalCarbs

    private val _totalProtein = MutableStateFlow(userData.getProtein())
    val totalProtein: StateFlow<Int> = _totalProtein

    private val _consumedCalories = MutableStateFlow(userData.getConsumedCaloriers())
    val consumedCalories:StateFlow<Int> = _consumedCalories

    // Macros
    private val _consumedFat =     MutableStateFlow(userData.getConsumedFat())
    private val _consumedProtein = MutableStateFlow(userData.getConsumedProtein())
    private val _consumedCarbs =   MutableStateFlow(userData.getConsumedCarbs())
    val consumedProtein:StateFlow<Int> =  _consumedProtein
    val consumedFat:StateFlow<Int> =      _consumedFat
    val consumedCarbs:StateFlow<Int> =    _consumedCarbs
    var lastFood: LastFood? = null



    @RequiresApi(Build.VERSION_CODES.O)
    fun addFoodDescription(foodDescription: String) {
        if(!model.checkDay(userData)){
            Log.d("API", "New Day")
            _totalCalories.value = userData.getTotalCalories()
            _totalFat.value = userData.getFat()
            _totalCarbs.value = userData.getCarbs()
            _totalProtein.value = userData.getProtein()
            _consumedCalories.value = userData.getConsumedCaloriers()
            _consumedFat.value = userData.getConsumedFat()
            _consumedCarbs.value = userData.getConsumedCarbs()
            _consumedProtein.value = userData.getConsumedProtein()
        }
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
            userData.setConsumedCaloriers(consumedCalories.value + (food.calories.replace("g", "").toDoubleOrNull()?.toInt() ?: 0))
            userData.setConsumedFat(consumedFat.value + (food.fat.replace("g", "").toDoubleOrNull()?.toInt() ?: 0))
            userData.setConsumedCarbs(consumedCarbs.value + (food.carbs.replace("g", "").toDoubleOrNull()?.toInt() ?: 0))
            userData.setConsumedProtein(consumedProtein.value + (food.protein.replace("g", "").toDoubleOrNull()?.toInt() ?: 0))

            //update state flow
            _consumedCalories.value += (food.calories.replace("g", "").toDoubleOrNull()?.toInt() ?: 0);
            _consumedFat.value += (food.fat.replace("g", "").toDoubleOrNull()?.toInt() ?: 0);
            _consumedCarbs.value += (food.carbs.replace("g", "").toDoubleOrNull()?.toInt() ?: 0);
            _consumedProtein.value += (food.protein.replace("g", "").toDoubleOrNull()?.toInt() ?: 0);
        }
        lastFood = null
    }

    fun rejectLastFood(){
        lastFood = null
    }
    fun setTotalCalories(calories: Int) {
        userData.setTotalCalories(calories)
        _totalCalories.value = calories
    }
    fun setTotalFat(fat: Int) {
        userData.setTotalFat(fat)
        _totalFat.value = fat
    }
    fun setTotalCarbs(carbs: Int) {
        userData.setTotalCarbs(carbs)
        _totalCarbs.value = carbs
    }
    fun setTotalProtein(protein: Int) {
        userData.setTotalProtein(protein)
        _totalProtein.value = protein
    }
    fun saveUserData() {
        userData.saveState()
    }

}