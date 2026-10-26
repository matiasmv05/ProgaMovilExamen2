package org.ucb.appp1

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import org.ucb.appp1.di.initKoinAndroid
import org.ucb.appp1.scheduler.LogScheduler

class MainApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        } catch (e: Exception) {
            // Manejar si se llama múltiples veces o ya fue inicializada
        }
        
        initKoinAndroid(this)

        val logScheduler = LogScheduler(this)
        logScheduler.schedulePeriodicalUpload()
    }
}