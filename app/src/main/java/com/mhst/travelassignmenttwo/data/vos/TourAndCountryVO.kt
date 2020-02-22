package com.mhst.travelassignmenttwo.data.vos

import com.mhst.architectureassignment.data.vos.BaseVO

/**
 * Created by Moe Htet on 22,February,2020
 */
data class TourAndCountryVO(
    val countries : List<BaseVO>,
    val tours : List<BaseVO>
)