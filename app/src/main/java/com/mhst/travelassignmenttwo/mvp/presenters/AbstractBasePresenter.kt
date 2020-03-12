package com.mhst.travelassignmenttwo.mvp.presenters

import androidx.lifecycle.ViewModel
import com.mhst.travelassignmenttwo.mvp.views.BaseView

/**
 * Created by Moe Htet on 13,March,2020
 */
abstract  class AbstractBasePresenter<T : BaseView> : BasePresenter<T>,ViewModel() {

    var mView : T? = null

    override fun initPresenter(view: T) {
        mView = view
    }

}