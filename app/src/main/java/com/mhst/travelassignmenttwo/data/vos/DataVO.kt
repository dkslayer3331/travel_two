package com.mhst.architectureassignment.data.vos

import com.google.gson.annotations.SerializedName

data class DataVO(
    @SerializedName("country")
    val country: List<BaseVO>,
    @SerializedName("popular_tours")
    val popularTours: List<BaseVO>
)