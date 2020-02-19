package com.mhst.architectureassignment.network.responses

import com.google.gson.annotations.SerializedName
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.data.vos.DataVO

data class ResponseVO(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<BaseVO>
){
    fun isSuccessful() = code == 200 && data!=null
}