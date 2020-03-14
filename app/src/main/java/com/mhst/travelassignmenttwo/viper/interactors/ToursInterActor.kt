package com.mhst.travelassignmenttwo.viper.interactors

import androidx.lifecycle.LiveData
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.viper.TourListContract

/**
 * Created by Moe Htet on 15,March,2020
 */
class ToursInterActor : TourListContract.InterActor {

    private val model : TourModel = TourModelImpl(TourApp.context)


    override fun getAllToursAndCountries(onError: (String) -> Unit): LiveData<TourAndCountryVO> {
     return model.getToursAndCountries()
    }

    override fun syncNewsListWithServer() {

    }


}