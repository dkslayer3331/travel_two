package com.mhst.travelassignmenttwo.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mhst.travelassignmenttwo.mvp.views.MainView

/**
 * Created by Moe Htet on 13,March,2020
 */
interface MainPresenter : BasePresenter<MainView>{
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onSwipeRrfresh(lifecycleOwner: LifecycleOwner)
}