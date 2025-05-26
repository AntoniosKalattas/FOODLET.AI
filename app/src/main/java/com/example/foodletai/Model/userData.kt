package com.example.foodletai.Model

class userData {
    private var totalCalories:Int=0;
    private var totalFat:Int=0;
    private var totalCarbs:Int=0;
    private var totalProtein:Int=0;
    private var totalFiber:Int=0;
    private var totalSugar:Int=0;

    fun getCalories():Int{return totalCalories;}
    fun getFat():Int{return totalFat;}
    fun getCarbs():Int{return totalCarbs;}
    fun getProtein():Int{return totalProtein;}
    fun getFiber():Int{return totalFiber;}
    fun getSugar():Int{return totalSugar;}
    fun setTotalCalories(calories:Int){this.totalCalories = calories}
    fun setTotalFat(fat:Int){this.totalFat = fat}
    fun setTotalCarbs(carbs:Int){this.totalCarbs = carbs}
    fun setTotalProtein(protein:Int){this.totalProtein = protein}
    fun setTotalFiber(fiber:Int){this.totalFiber = fiber}
    fun setTotalSugar(sugar:Int){this.totalSugar = sugar}
}