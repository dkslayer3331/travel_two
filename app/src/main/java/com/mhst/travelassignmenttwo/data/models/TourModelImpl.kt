package com.mhst.architectureassignment.data.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.network.responses.ResponseVO
import com.mhst.travelassignmenttwo.NO_CONNECTION_MESSAGE
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class TourModelImpl(context: Context) : TourModel, BaseModel() {

    private val db = TourDb.getInstance(context)

    init {
        combined()
    }

    var list = MutableLiveData<TourAndCountryVO>()

    override fun combined(){
        Log.d("combine","combine is called")
      Observable.zip(travelApi!!.getAllTours(),travelApi!!.getAllCountries(),
           BiFunction<ResponseVO,ResponseVO,TourAndCountryVO>{ tours : ResponseVO, countries : ResponseVO ->
               db.countryDao().deleteAllCountries()
               db.tourDao().deleteAllTours()
               db.tourDao().insertAllTours(tours.data)
               db.countryDao().insertAllCountries(countries.data)
               return@BiFunction TourAndCountryVO(listOf(), listOf())
           }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDataFromDb() : MutableLiveData<TourAndCountryVO>{
        Observable.zip<List<BaseVO>,List<BaseVO>,TourAndCountryVO>(db.tourDao().getAllTours(),db.countryDao().getAllTCountries(),
            BiFunction<List<BaseVO>,List<BaseVO>,TourAndCountryVO>{ tours , countries ->
                return@BiFunction TourAndCountryVO(countries,tours)
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                list.postValue(TourAndCountryVO(it.countries,it.tours))
            }
        return list
    }


    override fun getCountryDetail(name: String): LiveData<CountrVO> {
       return db.countryDao().getCountryDetail(name)
    }

    override fun tourDetail(name: String): LiveData<BaseVO> {
        return db.tourDao().getTourDetail(name)
    }


}