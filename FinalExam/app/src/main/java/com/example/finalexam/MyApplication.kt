package com.example.finalexam

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.finalexam.data.AppDatabase
import com.example.finalexam.data.ItemRepository
import com.example.finalexam.data.PreferencesManager
import com.example.finalexam.network.RetrofitClient
import com.example.finalexam.worker.JokeWorker
import java.util.concurrent.TimeUnit

class MyApplication : Application() {
    lateinit var database: AppDatabase
    lateinit var repository: ItemRepository
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(this)
        repository = ItemRepository(database.itemDao())
        preferencesManager = PreferencesManager(this)

        // Schedule periodic joke fetch every 30 minutes
        val workRequest = PeriodicWorkRequestBuilder<JokeWorker>(30, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "joke_fetch_work",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}

