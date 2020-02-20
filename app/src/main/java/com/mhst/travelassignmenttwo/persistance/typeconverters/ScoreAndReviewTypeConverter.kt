package com.mhst.travelassignmenttwo.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO

/**
 * Created by Moe Htet on 20,February,2020
 */

class ScoreAndReviewTypeConverter {

    @TypeConverter
    fun toJsonString(list : List<ScoreAndReviewVO>) : String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString : String) : List<ScoreAndReviewVO>{
        val type = object : TypeToken<List<ScoreAndReviewVO>>(){}.type
        return  Gson().fromJson(jsonString,type)
    }

}