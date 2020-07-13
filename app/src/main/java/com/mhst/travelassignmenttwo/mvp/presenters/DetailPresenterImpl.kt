package com.mhst.travelassignmenttwo.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mhst.travelassignmenttwo.data.models.TourModel
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.mvp.views.DetailView

/**
 * Created by Moe Htet on 13,March,2020
 */
class DetailPresenterImpl : DetailPresenter,AbstractBasePresenter<DetailView>(){

    lateinit var tourModel : TourModel

    override fun setUp(context: Context){
        tourModel = TourModelImpl(context)
    }

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