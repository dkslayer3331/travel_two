package com.mhst.travelassignmenttwo.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.mhst.travelassignmenttwo.mvp.views.DetailView

/**
 * Created by Moe Htet on 13,March,2020
 */
interface DetailPresenter : BasePresenter<DetailView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner,name : String,type : Int)
    fun setUp(context: Context)
}