package com.example.foodletai.api

import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ApiControl {
    private val apiKey: String = "insert_your_api_key_here" // Replace with your actual OpenRouter API key
    private val client = OkHttpClient()

    // Common working models on OpenRouter
    companion object {
        const val GPT_3_5_TURBO = "openai/gpt-3.5-turbo"
//        const val deepSeek = "deepseek/deepseek-r1:free"
//        const val CLAUDE_HAIKU = "anthropic/claude-3-haiku"
//        const val LLAMA_2_7B = "meta-llama/llama-2-7b-chat"
//        const val  MISTRAL_7B = "mistralai/mistral-7b-instruct"
    }

    suspend fun question(userMessage: String = "Tell me a Kotlin joke.", model: String = GPT_3_5_TURBO): String = withContext(Dispatchers.IO) {
        val json = """
        {
            "model": "$model",
            "messages": [
                { "role": "user", "content": "$userMessage" }
            ],
            "temperature": 0.7,
            "max_tokens": 10000
        }
        """.trimIndent()

        val requestBody = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("https://openrouter.ai/api/v1/chat/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .addHeader("HTTP-Referer", "https://github.com/your-app") // Optional: your app URL
            .addHeader("X-Title", "FoodletAI") // Your app name
            .post(requestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val body = response.body?.string()
                if (!response.isSuccessful || body == null) {
                    Log.e("API", "Error: ${response.code} ${response.message}")
                    return@withContext "Error: ${response.code} ${response.message}"
                }

                Log.d("API", "Raw response: $body")

                // Parse the JSON response to extract the actual message
                return@withContext parseResponse(body)
            }
        } catch (e: Exception) {
            Log.e("API", "Exception: ${e.message}")
            return@withContext "Exception: ${e.message}"
        }
    }

    private fun parseResponse(jsonResponse: String): String {
        return try {
            val jsonObject = JSONObject(jsonResponse)

            // Check if there's an error in the response
            if (jsonObject.has("error")) {
                val error = jsonObject.getJSONObject("error")
                val errorMessage = error.getString("message")
                Log.e("API", "API Error: $errorMessage")
                return "API Error: $errorMessage"
            }

            // Extract the message content from choices array
            val choices = jsonObject.getJSONArray("choices")
            if (choices.length() > 0) {
                val firstChoice = choices.getJSONObject(0)
                val message = firstChoice.getJSONObject("message")
                val content = message.getString("content")

                // Log usage information if available
                if (jsonObject.has("usage")) {
                    val usage = jsonObject.getJSONObject("usage")
                    val totalTokens = usage.getInt("total_tokens")
                    Log.d("API", "Tokens used: $totalTokens")
                }

                content
            } else {
                Log.e("API", "No choices in response")
                "No response received"
            }
        } catch (e: Exception) {
            Log.e("API", "JSON parsing error: ${e.message}")
            "Error parsing response: ${e.message}"
        }
    }

//    // Additional method for multi-turn conversations
//    suspend fun conversation(messages: List<Pair<String, String>>): String = withContext(Dispatchers.IO) {
//        val messagesArray = StringBuilder("[")
//        messages.forEachIndexed { index, (role, content) ->
//            if (index > 0) messagesArray.append(",")
//            messagesArray.append("""{"role": "$role", "content": "$content"}""")
//        }
//        messagesArray.append("]")
//
//        val json = """
//        {
//            "model": "openai/gpt-3.5-turbo",
//            "messages": ${messagesArray},
//            "temperature": 0.7,
//            "max_tokens": 1000
//        }
//        """.trimIndent()
//
//        val requestBody = json.toRequestBody("application/json".toMediaType())
//
//        val request = Request.Builder()
//            .url("https://openrouter.ai/api/v1/chat/completions")
//            .addHeader("Authorization", "Bearer $apiKey")
//            .addHeader("Content-Type", "application/json")
//            .addHeader("HTTP-Referer", "https://github.com/your-app")
//            .addHeader("X-Title", "FoodletAI")
//            .post(requestBody)
//            .build()
//
//        try {
//            client.newCall(request).execute().use { response ->
//                val body = response.body?.string()
//                if (!response.isSuccessful || body == null) {
//                    Log.e("API", "Error: ${response.code} ${response.message}")
//                    return@withContext "Error: ${response.code} ${response.message}"
//                }
//
//                Log.d("API", "Raw response: $body")
//                return@withContext parseResponse(body)
//            }
//        } catch (e: Exception) {
//            Log.e("API", "Exception: ${e.message}")
//            return@withContext "Exception: ${e.message}"
//        }
//    }
//
//    // Test method to check if API key and connection work
//    suspend fun testConnection(): String = withContext(Dispatchers.IO) {
//        // First, let's test with a simple request to get models
//        val request = Request.Builder()
//            .url("https://openrouter.ai/api/v1/models")
//            .addHeader("Authorization", "Bearer $apiKey")
//            .addHeader("HTTP-Referer", "https://github.com/your-app")
//            .addHeader("X-Title", "FoodletAI")
//            .get()
//            .build()
//
//        try {
//            client.newCall(request).execute().use { response ->
//                val body = response.body?.string()
//                Log.d("API", "Test connection - Status: ${response.code}")
//                Log.d("API", "Test connection - Response: $body")
//
//                if (!response.isSuccessful) {
//                    return@withContext "Connection test failed: ${response.code} ${response.message}"
//                }
//
//                return@withContext "Connection successful! Status: ${response.code}"
//            }
//        } catch (e: Exception) {
//            Log.e("API", "Test connection exception: ${e.message}")
//            return@withContext "Connection test exception: ${e.message}"
//        }
//    }
//    suspend fun getAvailableModels(): List<String> = withContext(Dispatchers.IO) {
//        val request = Request.Builder()
//            .url("https://openrouter.ai/api/v1/models")
//            .addHeader("Authorization", "Bearer $apiKey")
//            .get()
//            .build()
//
//        try {
//            client.newCall(request).execute().use { response ->
//                val body = response.body?.string()
//                if (!response.isSuccessful || body == null) {
//                    Log.e("API", "Error fetching models: ${response.code}")
//                    return@withContext emptyList()
//                }
//
//                val jsonObject = JSONObject(body)
//                val dataArray = jsonObject.getJSONArray("data")
//                val models = mutableListOf<String>()
//
//                for (i in 0 until dataArray.length()) {
//                    val model = dataArray.getJSONObject(i)
//                    models.add(model.getString("id"))
//                }
//
//                Log.d("API", "Available models: ${models.size}")
//                models
//            }
//        } catch (e: Exception) {
//            Log.e("API", "Exception fetching models: ${e.message}")
//            emptyList()
//        }
//    }
}