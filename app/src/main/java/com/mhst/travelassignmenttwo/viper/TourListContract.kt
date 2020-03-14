package com.mhst.travelassignmenttwo.viper

import androidx.lifecycle.LiveData
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.delegates.TourDelegate

/**
 * Created by Moe Htet on 15,March,2020
 */
class TourListContract {
    interface View {
        fun displayToursAndCountries(countries : List<BaseVO>,tours : List<BaseVO>)
        fun showLoading()
        fun hideLoading()
        fun showEmptyView()
        fun hideEmptyView()
        fun showErrorMessage(errorMessage: String)
    }

    interface Presenter : TourDelegate{
        fun onUiReady()
        fun onUiDestroyed()
        fun onSwipeRefresh()
    }

    interface InterActor {
        fun getAllNews(onError: (String) -> Unit): LiveData<TourAndCountryVO>
        fun syncNewsListWithServer()
    }

    interface InterActorOutput {
        fun onNewsListFetchSuccess(countries : List<BaseVO>,tours : List<BaseVO>)
        fun onNewsListFetchFailure(errorMessage: String)
        fun onNewsListEmpty()
    }

}