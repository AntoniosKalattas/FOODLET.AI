package com.example.foodletai.ViewModel

import com.example.foodletai.Model.Model
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel


class MainViewModel:ViewModel() {
    private val model = Model();
    private val _godAnswer = MutableStateFlow("")
    val answer: StateFlow<String> = _godAnswer

    fun addFoodDescription(foodDescription: String) {
        _godAnswer.value = model.askGod(viewModelScope, foodDescription);
    }

}