package com.mhst.travelassignmenttwo.mvp.views

import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO

/**
 * Created by Moe Htet on 13,March,2020
 */
interface MainView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showLists(list : TourAndCountryVO)
    fun showErrorMessage(message : String)
    fun displayEmptyView()
    fun navigateDetail(name : String,type : Int)
}