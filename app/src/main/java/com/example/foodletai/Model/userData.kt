package com.example.foodletai.Model


class userData {
    private var totalCalories:Int=2200;
    private var consumedCaloriers:Int=0;
    private var totalFat:Int=0;
    private var totalCarbs:Int=0;
    private var totalProtein:Int=220;
    private var totalFiber:Int=0;
    private var totalSugar:Int=0;

    private var consumedFat:Int=0;
    private var consumedCarbs:Int=0;
    private var consumedProtein:Int=0;
    private var consumedFiber:Int=0;
    private var consumedSugar:Int=0;


    fun getTotalCalories():Int{return totalCalories;}
    fun getConsumedCaloriers():Int{return consumedCaloriers;}
    fun getFat():Int{return totalFat;}
    fun getCarbs():Int{return totalCarbs;}
    fun getProtein():Int{return totalProtein;}
    fun getFiber():Int{return totalFiber;}
    fun getSugar():Int{return totalSugar;}
    fun getConsumedFat():Int{return consumedFat;}
    fun getConsumedCarbs():Int{return consumedCarbs;}
    fun getConsumedProtein():Int{return consumedProtein;}
    fun getConsumedFiber():Int{return consumedFiber;}
    fun getConsumedSugar():Int{return consumedSugar;}
    fun setTotalCalories(calories:Int){this.totalCalories = calories}
    fun setTotalFat(fat:Int){this.totalFat = fat}
    fun setTotalCarbs(carbs:Int){this.totalCarbs = carbs}
    fun setTotalProtein(protein:Int){this.totalProtein = protein}
    fun setTotalFiber(fiber:Int){this.totalFiber = fiber}
    fun setTotalSugar(sugar:Int){this.totalSugar = sugar}
    fun setConsumedCaloriers(caloriers:Int){this.consumedCaloriers = caloriers}
    fun setConsumedFat(fat:Int){this.consumedFat = fat}
    fun setConsumedCarbs(carbs:Int){this.consumedCarbs = carbs}
    fun setConsumedProtein(protein:Int){this.consumedProtein = protein}


}