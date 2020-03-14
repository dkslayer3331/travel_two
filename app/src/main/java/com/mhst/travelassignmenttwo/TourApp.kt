package com.mhst.travelassignmenttwo

import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

/**
 * Created by Moe Htet on 22,February,2020
 */
class TourApp : Application() {

    init {
        context = this
    }


    override fun onCreate() {
        super.onCreate()
        getToursOneTime()
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