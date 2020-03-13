package com.mhst.travelassignmenttwo.mvp.views

import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO

/**
 * Created by Moe Htet on 13,March,2020
 */
interface DetailView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(detailObj : BaseVO)
    fun countryDetail(detailObj : CountrVO)
}