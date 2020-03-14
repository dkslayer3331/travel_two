package com.mhst.travelassignmenttwo.data.models

import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import io.reactivex.Observable
import java.util.*

interface TourModel {
    fun getCountryDetail(name : String) : LiveData<CountrVO>
    fun tourDetail(name : String) : LiveData<BaseVO>
    fun getToursAndCountries() : LiveData<TourAndCountryVO>
    fun getFromApiAndSaveLocal(onSuccess : ()-> Unit,onError: (String) -> Unit)
}