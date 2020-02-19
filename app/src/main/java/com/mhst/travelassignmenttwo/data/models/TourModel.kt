package com.mhst.architectureassignment.data.models

import com.mhst.architectureassignment.data.vos.BaseVO

interface TourModel {
    fun getAllList(onSucess : (List<BaseVO>,List<BaseVO>) -> Unit,onFail : (String) -> Unit)

    fun getCountry(id : Int) : BaseVO

    fun getTourDetail(id : Int) : BaseVO

}