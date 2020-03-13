package com.mhst.travelassignmenttwo

import android.app.Application
import android.content.Context

/**
 * Created by Moe Htet on 22,February,2020
 */
class TourApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
       // TourModelImpl.initModel(applicationContext)
    }

    companion object{
        lateinit var context : Context
    }

}