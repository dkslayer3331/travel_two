package com.mhst.architectureassignment.data.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import io.reactivex.Observable
import java.util.*

interface TourModel {
    fun getCountryDetail(name : String) : LiveData<CountrVO>
    fun tourDetail(name : String) : LiveData<BaseVO>
    fun  getDataFromDb(): MutableLiveData<TourAndCountryVO>
    fun combined()
}