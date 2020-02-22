package com.mhst.travelassignmenttwo.persistance.dbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.persistance.daos.CoyntryDao
import com.mhst.travelassignmenttwo.persistance.daos.TourDao
import com.mhst.travelassignmenttwo.persistance.typeconverters.PhotoUrlConverter
import com.mhst.travelassignmenttwo.persistance.typeconverters.ScoreAndReviewTypeConverter

/**
 * Created by Moe Htet on 20,February,2020
 */
@Database(entities = [BaseVO::class,CountrVO::class],version = 2,exportSchema = false)
@TypeConverters(PhotoUrlConverter::class, ScoreAndReviewTypeConverter::class)
abstract class TourDb : RoomDatabase(){

    abstract fun tourDao() : TourDao

    abstract fun countryDao() : CoyntryDao

    companion object{
        val DB_NAME = "TOURS_AND_COUNTRIES.DB"
        var dbInstance : TourDb? = null
        fun getInstance(context: Context) : TourDb{
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context,TourDb::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance!!
        }
    }

}