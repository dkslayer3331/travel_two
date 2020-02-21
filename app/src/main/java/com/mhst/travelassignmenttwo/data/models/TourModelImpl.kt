package com.mhst.architectureassignment.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.network.responses.ResponseVO
import com.mhst.travelassignmenttwo.NO_CONNECTION_MESSAGE
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.function.Function

class TourModelImpl(context: Context) : TourModel, BaseModel() {

    var errorMessage = ""

    init {
        combined()
    }

    override fun combined() {
       Observable.zip(travelApi!!.getAllTours(),travelApi!!.getAllCountries(),
           BiFunction { tours : ResponseVO, countries : ResponseVO ->
               db.tourDao().insertAllTours(tours.data)
               db.tourDao().insertAllCountries(countries.data)
           }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .onErrorReturn {
               errorMessage = it.localizedMessage ?: NO_CONNECTION_MESSAGE
           }
    }

    private val db = TourDb.getInstance(context)

    override fun getCountries(onError: (String) -> Unit): LiveData<List<BaseVO>> {
//        travelApi!!.getAllCountries().map { it.data }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                db.tourDao().insertAllCountries(it)
//            },{
//                onError(it.localizedMessage)
//            })
        onError(errorMessage)

        return db.tourDao().getAllTCountries()

    }

    override fun getTours(onError: (String) -> Unit): LiveData<List<BaseVO>> {

//        travelApi!!.getAllTours().map { it.data }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                db.tourDao().insertAllTours(it)
//            },{
//                onError(it.localizedMessage)
//            })
        onError(errorMessage)

        return db.tourDao().getAllTours()

    }

    override fun getCountryDetail(id: Int): LiveData<CountrVO> {
       return db.tourDao().getCountryDetail(id)
    }

    override fun tourDetail(id: Int): LiveData<BaseVO> {
        return db.tourDao().getTourDetail(id)
    }


}