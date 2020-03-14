package com.mhst.travelassignmenttwo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.delegate.TourDelegate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Moe Htet on 14,March,2020
 */
class MainViewModel : ViewModel(),TourDelegate{

    val model : TourModel = TourModelImpl(TourApp.context)

    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    private val navigateToNewsDetailsLiveData: MutableLiveData<Pair<String,Int>> = MutableLiveData()

    private val enableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()

    private val disableSwipeRefreshLiveData: MutableLiveData<Unit> = MutableLiveData()

   fun getCombinedList() : MutableLiveData<TourAndCountryVO>{
       return model.getDataFromDb()
   }

    fun getErrorLiveData(): LiveData<String> {
        return errorLiveData
    }

    override fun onTap(name: String, type: Int) {
        navigateToNewsDetailsLiveData.postValue(Pair(name,type))
    }

    fun getNavigateToNewsDetailsLiveData(): MutableLiveData<Pair<String, Int>> {
        return navigateToNewsDetailsLiveData
    }

    fun getEnableSwipeRefreshLiveData() : LiveData<Unit>{
        return enableSwipeRefreshLiveData
    }

    fun getDisableSwipeRefreshLiveData() : LiveData<Unit>{
        return disableSwipeRefreshLiveData
    }

    fun onSwipeRefresh(){
        enableSwipeRefreshLiveData.postValue(Unit)
        model.combined()
        disableSwipeRefreshLiveData.postValue(Unit)
    }


}