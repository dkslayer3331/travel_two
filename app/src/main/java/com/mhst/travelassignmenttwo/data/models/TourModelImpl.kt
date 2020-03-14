package com.mhst.travelassignmenttwo.data.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import com.mhst.architectureassignment.data.models.BaseModel
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.network.responses.ResponseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class TourModelImpl(context: Context) : TourModel, BaseModel() {

    var errorMessage = ""

    //one time with work manager

    override fun getFromApiAndSaveLocal(onSuccess: () -> Unit, onError: (String) -> Unit) {
        Log.d("combine","combine is called")
      Observable.zip(travelApi!!.getAllTours(),travelApi!!.getAllCountries(),
           BiFunction<ResponseVO,ResponseVO,TourAndCountryVO>{ tours : ResponseVO, countries : ResponseVO ->
               return@BiFunction TourAndCountryVO(countries.data,tours.data)
           }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
          .subscribe({
              db.countryDao().deleteAllCountries()
              db.tourDao().deleteAllTours()
              db.tourDao().insertAllTours(it.tours)
              db.countryDao().insertAllCountries(it.countries)
          },{
                onError(it.localizedMessage)
          })
    }

    private val db = TourDb.getInstance(context)

    override fun getCountryDetail(name: String): LiveData<CountrVO> {
       return db.countryDao().getCountryDetail(name)
    }

    override fun tourDetail(name: String): LiveData<BaseVO> {
        return db.tourDao().getTourDetail(name)
    }

    //from local storage
    override fun getToursAndCountries(): LiveData<TourAndCountryVO> {
       val flowableStream = Flowable.zip(db.tourDao().getAllTours(),db.countryDao().getAllTCountries(),
           BiFunction<List<BaseVO>,List<BaseVO>,TourAndCountryVO> {
                   tours , countries -> return@BiFunction TourAndCountryVO(tours,countries)

           })
        return fromPublisher(flowableStream)
    }


}