package com.mhst.travelassignmenttwo

import android.app.Application
import android.content.Context
import com.mhst.architectureassignment.data.models.TourModelImpl

/**
 * Created by Moe Htet on 22,February,2020
 */
class TourApp : Application() {

    init {
        context = this
    }

    override fun onCreate() {
        super.onCreate()
       // TourModelImpl.initModel(applicationContext)
    }

    companion object{
        lateinit var context : Context
    }

}