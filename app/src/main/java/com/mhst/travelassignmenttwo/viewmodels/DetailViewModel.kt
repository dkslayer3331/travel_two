package com.mhst.travelassignmenttwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.vos.CountrVO

/**
 * Created by Moe Htet on 14,March,2020
 */
class DetailViewModel : ViewModel() {

    val model : TourModel  = TourModelImpl(TourApp.context)

    fun getCountryLiveData(name : String) : LiveData<CountrVO>{
        return  model.getCountryDetail(name)
    }

    fun getTourDetail(name : String) : LiveData<BaseVO>{
        return model.tourDetail(name)
    }



}