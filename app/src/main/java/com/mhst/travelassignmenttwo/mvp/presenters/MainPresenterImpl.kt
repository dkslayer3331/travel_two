package com.mhst.travelassignmenttwo.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mhst.travelassignmenttwo.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.reactivex.Scheduler

/**
 * Created by Moe Htet on 13,March,2020
 */
class MainPresenterImpl() : MainPresenter, AbstractBasePresenter<MainView>() {

    lateinit var model: TourModel

    lateinit var schedulers: Scheduler

    lateinit var androidSchedulers: Scheduler

    fun setUp(context: Context,scheduler: Scheduler,androidScheduler : Scheduler){
        model = TourModelImpl(context)
        schedulers = scheduler
        androidSchedulers = androidScheduler
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        requestAllData(lifecycleOwner)
    }

    override fun onSwipeRefresh(lifecycleOwner: LifecycleOwner) {
        requestAllData(lifecycleOwner)
    }

    override fun onTap(name: String, type: Int) {
            mView?.navigateDetail(name,type)
    }

    private fun requestAllData(lifecycleOwner: LifecycleOwner) {
        mView?.showLoading()
        model.combined(schedulers)
            .subscribeOn(schedulers)
            .observeOn(androidSchedulers)
            .doOnError { mView?.showErrorMessage(it.localizedMessage) }
            .subscribe {
                mView?.hideLoading()
                if (it.countries.isEmpty() && it.tours.isEmpty()) mView?.displayEmptyView()
                else mView?.showLists(it)
            }
    }


}