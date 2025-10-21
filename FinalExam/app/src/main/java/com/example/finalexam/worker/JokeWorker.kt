package com.example.finalexam.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.finalexam.data.PreferencesManager
import com.example.finalexam.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val api = RetrofitClient.create()
            val resp = try { api.getRandomJoke() } catch (e: Exception) { null }
            val jokeResp = resp?.body()
            val text = if (jokeResp != null) {
                val setup = jokeResp.setup ?: ""
                val punch = jokeResp.punchline ?: ""
                "$setup\n$punch"
            } else {
                "No joke available"
            }
            val prefs = PreferencesManager(applicationContext)
            prefs.setLastJoke(text)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

