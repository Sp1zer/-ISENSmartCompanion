package fr.isen.fougeres.isensmartcompanion.ai
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import fr.isen.fougeres.isensmartcompanion.BuildConfig

val model = GenerativeModel(
    modelName = "gemini-1.5-flash-001",
    apiKey = BuildConfig.GEMINI_API_KEY,
    generationConfig = generationConfig {
        temperature = 0.15f
        topK = 32
        topP = 1f
        maxOutputTokens = 4096
    },
    safetySettings = null
)