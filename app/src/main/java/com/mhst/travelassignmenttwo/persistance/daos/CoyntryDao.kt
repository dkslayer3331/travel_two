package com.mhst.travelassignmenttwo.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import io.reactivex.Observable

/**
 * Created by Moe Htet on 22,February,2020
 */
@Dao
interface CoyntryDao {
    @Query("select * from countries ")
    fun getAllTCountries() : Observable<List<BaseVO>>

    @Insert(entity = CountrVO::class)
    fun insertAllCountries(list : List<BaseVO>)

    @Query("select * from countries where name =:name")
    fun getCountryDetail(name: String) : LiveData<CountrVO>

    @Query("delete from countries")
    fun deleteAllCountries()
}