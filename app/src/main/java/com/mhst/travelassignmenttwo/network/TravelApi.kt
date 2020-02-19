package com.mhst.travelassignmenttwo.network

import com.mhst.architectureassignment.network.responses.ResponseVO
import com.mhst.travelassignmenttwo.GET_ALL
import com.mhst.travelassignmenttwo.GET_ALL_COUNTRIES
import retrofit2.Call
import retrofit2.http.GET

interface TravelApi {

    @GET(GET_ALL)
    fun getAllTours() : Call<ResponseVO>

    @GET(GET_ALL_COUNTRIES)
    fun getAllCountries() : Call<ResponseVO>

}