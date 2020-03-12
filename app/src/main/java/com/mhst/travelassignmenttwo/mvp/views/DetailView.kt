package com.mhst.travelassignmenttwo.mvp.views

import com.mhst.architectureassignment.data.vos.BaseVO

/**
 * Created by Moe Htet on 13,March,2020
 */
interface DetailView : BaseView {
    fun showDetail(detailObj : BaseVO)
}