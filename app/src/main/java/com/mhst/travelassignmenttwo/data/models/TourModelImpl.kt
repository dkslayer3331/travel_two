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
import com.mhst.travelassignmenttwo.mvp.presenters.MainPresenter
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class TourModelImpl(context: Context) : TourModel, BaseModel() {

    var errorMessage = ""

    var list = MutableLiveData<TourAndCountryVO>()

    override fun combined(): Observable<TourAndCountryVO> {
        Log.d("combine","combine is called")
     return Observable.zip(travelApi!!.getAllTours(),travelApi!!.getAllCountries(),
           BiFunction<ResponseVO,ResponseVO,TourAndCountryVO>{ tours : ResponseVO, countries : ResponseVO ->
             //  if(!tours.isSuccessful() && !tours.isSuccessful()) error("Something went wrong")
               db.countryDao().deleteAllCountries()
               db.tourDao().deleteAllTours()
               db.tourDao().insertAllTours(tours.data)
               db.countryDao().insertAllCountries(countries.data)
               val countries = db.countryDao().getAllTCountries()
               val tours = db.tourDao().getAllTours()
               return@BiFunction TourAndCountryVO(countries,tours)
           }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    private val db = TourDb.getInstance(context)

    override fun getCountries(onError: (String) -> Unit): List<BaseVO> {
        onError(errorMessage)
        return db.countryDao().getAllTCountries()

    }

    override fun getTours(onError: (String) -> Unit): List<BaseVO> {
        onError(errorMessage)
        return db.tourDao().getAllTours()
    }

    override fun getCountryDetail(name: String): LiveData<CountrVO> {
       return db.countryDao().getCountryDetail(name)
    }

    override fun tourDetail(name: String): LiveData<BaseVO> {
        return db.tourDao().getTourDetail(name)
    }


}