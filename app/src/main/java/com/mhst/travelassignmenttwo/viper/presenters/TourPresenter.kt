package com.mhst.travelassignmenttwo.viper.presenters

import androidx.lifecycle.Observer
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.fragments.HomeFragment
import com.mhst.travelassignmenttwo.activities.DetailActivity
import com.mhst.travelassignmenttwo.viper.TourListContract
import ru.terrakok.cicerone.Router

/**
 * Created by Moe Htet on 15,March,2020
 */
class TourPresenter(
    private var mView : TourListContract.View?,
    private var mInteractor : TourListContract.InterActor?,
    private var mRouter : Router?
) : TourListContract.InterActorOutput,TourListContract.Presenter {
    override fun onUiReady() {
        mView?.showLoading()
        mInteractor?.getAllToursAndCountries {
            onFetchail(it)
        }?.observe(mView as HomeFragment, Observer {
            onFetchSuccess(it.countries,it.tours)
        })
    }

    override fun onUiDestroyed() {
        mView = null
        mInteractor = null
    }

    override fun onSwipeRefresh() {
        mInteractor?.syncNewsListWithServer()
    }

    override fun onTap(name: String, type: Int) {
        mRouter?.navigateTo(DetailActivity.TAG,Pair(name,type))
    }

    override fun onFetchSuccess(countries: List<BaseVO>, tours: List<BaseVO>) {
        mView?.hideLoading()
        mView?.hideEmptyView()
        mView?.displayToursAndCountries(countries,tours)
    }

    override fun onFetchail(errorMessage: String) {
        mView?.hideLoading()
        mView?.showErrorMessage(errorMessage)
    }

    override fun onEmpty() {
        mView?.hideLoading()
        mView?.showEmptyView()
    }
}