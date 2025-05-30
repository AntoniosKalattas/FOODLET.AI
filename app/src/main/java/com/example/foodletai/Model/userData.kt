package com.example.foodletai.Model

import android.content.Context
import kotlin.apply

class userData(context: Context) {
    private var context:Context=context;
    private var totalCalories:Int=2200;
    private var totalProtein:Int=220;
    private var totalFat:Int=0;
    private var totalCarbs:Int=0;

    private var consumedCaloriers:Int=0;
    private var consumedProtein:Int=0;
    private var consumedFat:Int=0;
    private var consumedCarbs:Int=0;

    fun getTotalCalories():Int{return totalCalories;}
    fun getConsumedCaloriers():Int{return consumedCaloriers;}
    fun getFat():Int{return totalFat;}
    fun getCarbs():Int{return totalCarbs;}
    fun getProtein():Int{return totalProtein;}
    fun getConsumedFat():Int{return consumedFat;}
    fun getConsumedCarbs():Int{return consumedCarbs;}
    fun getConsumedProtein():Int{return consumedProtein;}
    fun setTotalCalories(calories:Int){this.totalCalories = calories}
    fun setTotalFat(fat:Int){this.totalFat = fat}
    fun setTotalCarbs(carbs:Int){this.totalCarbs = carbs}
    fun setTotalProtein(protein:Int){this.totalProtein = protein}
    fun setConsumedCaloriers(caloriers:Int){this.consumedCaloriers = caloriers; saveState()}
    fun setConsumedFat(fat:Int){this.consumedFat = fat; saveState()}
    fun setConsumedCarbs(carbs:Int){this.consumedCarbs = carbs; saveState()}
    fun setConsumedProtein(protein:Int){this.consumedProtein = protein; saveState()}

    fun saveState() {
        val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("totalCalories", totalCalories)
            putInt("totalFat", totalFat)
            putInt("totalCarbs", totalCarbs)
            putInt("totalProtein", totalProtein)
            putInt("consumedCaloriers", consumedCaloriers)
            putInt("consumedFat", consumedFat)
            putInt("consumedCarbs", consumedCarbs)
            putInt("consumedProtein", consumedProtein)
            apply()
        }
    }

    fun loadState() {
        val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        totalCalories = sharedPref.getInt("totalCalories", 2200)
        totalFat = sharedPref.getInt("totalFat", 0)
        totalCarbs = sharedPref.getInt("totalCarbs", 0)
        totalProtein = sharedPref.getInt("totalProtein", 220)

        consumedCaloriers = sharedPref.getInt("consumedCaloriers", 0)
        consumedFat = sharedPref.getInt("consumedFat", 0)
        consumedCarbs = sharedPref.getInt("consumedCarbs", 0)
        consumedProtein = sharedPref.getInt("consumedProtein", 0)
    }

    fun resetDayData(){
        consumedCaloriers = 0
        consumedFat = 0
        consumedCarbs = 0
        consumedProtein = 0
        saveState() // Save the reset state
    }
    init{
        loadState();// start loading the state
    }

}