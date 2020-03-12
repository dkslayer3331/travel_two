package com.mhst.travelassignmenttwo.mvp.presenters

import com.mhst.travelassignmenttwo.mvp.views.DetailView

/**
 * Created by Moe Htet on 13,March,2020
 */
interface DetailPresenter : BasePresenter<DetailView> {
    fun onUiReady()
}