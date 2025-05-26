package com.example.foodletai.Model

import com.example.foodletai.API.ApiControl
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Model() {
    private var god:ApiControl;
    fun askGod(scope: CoroutineScope, question: String, onResult: (String) -> Unit){
        scope.launch {
            val result =
                god.question(userMessage = question)  // assuming `god` is an instance of ApiControl
            Log.d("API", "Response: $result")
            onResult(result)
        }
    }

    init {
        god = ApiControl();
    }
}