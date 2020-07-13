package com.mhst.travelassignmenttwo.data.models

import com.mhst.travelassignmenttwo.BASE_URL
import com.mhst.travelassignmenttwo.network.TravelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class BaseModel {

    protected var travelApi : TravelApi? = null
//    lateinit var mContext: Context

    init {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level  = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder)
            .build()

        travelApi = retrofit.create(TravelApi::class.java)
    }

//    fun initModel(context: Context){
//        mContext=context
//    }



}