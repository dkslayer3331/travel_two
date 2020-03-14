package com.mhst.travelassignmenttwo.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import io.reactivex.Observable

/**
 * Created by Moe Htet on 20,February,2020
 */
@Dao
interface TourDao {

    //tours

    @Query("select * from tours ")
    fun getAllTours() : Observable<List<BaseVO>>

    @Insert(entity = BaseVO::class,onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTours(list : List<BaseVO>)

    @Query("select * from tours where name =:name ")
    fun getTourDetail(name: String) : LiveData<BaseVO>

    @Query("delete from tours")
    fun deleteAllTours()

}