package com.mhst.travelassignmenttwo.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Moe Htet on 13,March,2020
 */
class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val model: TourModel = TourModelImpl(TourApp.context)

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        requestAllData(lifecycleOwner)
    }

    override fun onSwipeRrfresh(lifecycleOwner: LifecycleOwner) {
        requestAllData(lifecycleOwner)
    }

    override fun onTap(name: String, type: Int) {
            mView?.navigateDetail(name,type)
    }

    private fun requestAllData(lifecycleOwner: LifecycleOwner) {
        mView?.showLoading()
        model.combined()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { mView?.showErrorMessage(it.localizedMessage) }
            .subscribe {
                mView?.hideLoading()
                if (it.countries.isEmpty() && it.tours.isEmpty()) mView?.displayEmptyView()
                else mView?.showLists(it)
            }
    }


}