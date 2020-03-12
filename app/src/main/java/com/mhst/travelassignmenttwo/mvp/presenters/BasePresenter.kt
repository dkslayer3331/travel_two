package com.mhst.travelassignmenttwo.mvp.presenters

import com.mhst.travelassignmenttwo.mvp.views.BaseView

/**
 * Created by Moe Htet on 13,March,2020
 */
interface BasePresenter<T : BaseView> {

    fun initPresenter(view : T)

}