package com.mhst.architectureassignment.data.models

import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import java.util.*

interface TourModel {
   fun getCountries(onError : (String) -> Unit) : LiveData<List<BaseVO>>
    fun getTours(onError: (String) -> Unit) : LiveData<List<BaseVO>>
    fun getCountryDetail(name : String) : LiveData<CountrVO>
    fun tourDetail(name : String) : LiveData<BaseVO>

    fun combined()
}