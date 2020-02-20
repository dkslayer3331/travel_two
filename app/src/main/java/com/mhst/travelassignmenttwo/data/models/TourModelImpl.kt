package com.mhst.architectureassignment.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TourModelImpl(context: Context) : TourModel, BaseModel() {

    private val db = TourDb.getInstance(context)

    override fun getCountries(onError: (String) -> Unit): LiveData<List<BaseVO>> {
        travelApi!!.getAllCountries().map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.tourDao().insertAllCountries(it)
            },{
                onError(it.localizedMessage)
            })

        return db.tourDao().getAllTCountries()

    }

    override fun getTours(onError: (String) -> Unit): LiveData<List<BaseVO>> {

        travelApi!!.getAllTours().map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.tourDao().insertAllTours(it)
            },{
                onError(it.localizedMessage)
            })

        return db.tourDao().getAllTours()

    }

    override fun getCountryDetail(name: String): LiveData<CountrVO> {
       return db.tourDao().getCountryDetail(name)
    }

    override fun tourDetail(name: String): LiveData<BaseVO> {
        return db.tourDao().getTourDetail(name)
    }


}