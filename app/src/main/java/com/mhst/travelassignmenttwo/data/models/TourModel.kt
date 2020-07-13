package com.mhst.travelassignmenttwo.data.models

import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.*

interface TourModel {
   fun getCountries(onError : (String) -> Unit) : List<BaseVO>
    fun getTours(onError: (String) -> Unit) : List<BaseVO>
    fun getCountryDetail(name : String) : LiveData<CountrVO>
    fun tourDetail(name : String) : LiveData<BaseVO>
    fun combined(scheduler: Scheduler) : Observable<TourAndCountryVO>
}