package com.mhst.travelassignmenttwo.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Moe Htet on 20,February,2020
 */
class PhotoUrlConverter {

    @TypeConverter
    fun toList(jsonString : String) : List<String>{
        return Gson().fromJson(jsonString,object :TypeToken<List<String>>(){}.type)
    }

    @TypeConverter
    fun toJsonString(list : List<String>) : String{
        return Gson().toJson(list)
    }


}