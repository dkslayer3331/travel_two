package com.mhst.travelassignmenttwo.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.mvp.views.DetailView
import com.mhst.travelassignmenttwo.persistance.daos.CoyntryDao

/**
 * Created by Moe Htet on 13,March,2020
 */
class DetailPresenterImpl : DetailPresenter,AbstractBasePresenter<DetailView>(){

    private val tourModel : TourModel = TourModelImpl(TourApp.context)

    //country -> 1 , tour -> 2

    override fun onUiReady(lifecycleOwner: LifecycleOwner, name: String, type: Int) {
      if(type == 2) tourModel.tourDetail(name).observe(lifecycleOwner, Observer {
          mView?.showDetail(it)
      })
        else tourModel.getCountryDetail(name).observe(lifecycleOwner, Observer {
          mView?.countryDetail(it)
      })
    }
}