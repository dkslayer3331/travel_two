package com.mhst.travelassignmenttwo.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO

/**
 * Created by Moe Htet on 20,February,2020
 */
@Dao
interface TourDao {

    //tours

    @Query("select * from tours ")
    fun getAllTours() : LiveData<List<BaseVO>>

    @Insert(entity = BaseVO::class)
    fun insertAllTours(list : List<BaseVO>)

    @Query("select * from tours where name LIKE :name ")
    fun getTourDetail(name : String) : LiveData<BaseVO>

    //Countries

    @Query("select * from countries ")
    fun getAllTCountries() : LiveData<List<BaseVO>>

    @Insert(entity = CountrVO::class)
    fun insertAllCountries(list : List<BaseVO>)

    @Query("select * from countries where name LIKE :name ")
    fun getCountryDetail(name : String) : LiveData<CountrVO>


}