package com.mhst.travelassignmenttwo

import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * Created by Moe Htet on 22,February,2020
 */
class TourApp : Application() {

    init {
        context = this
    }

    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        getToursOneTime()
        initCicerone()
    }

    private fun initCicerone(){
        cicerone = Cicerone.create()
    }

    companion object{
        lateinit var context : Context
    }

    private fun getToursOneTime(){
        val getEventsWorkRequest = OneTimeWorkRequest
            .Builder(TourWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWorkRequest)
    }


}