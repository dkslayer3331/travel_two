package com.mhst.architectureassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewholders.CountryViewHolder
import com.mhst.travelassignmenttwo.R
import com.mhst.travelassignmenttwo.delegate.TourDelegate

class CountryAdapter(val delegate: TourDelegate) : BaseRecyclerAdapter<BaseVO, CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_country_item,parent,false)
        return CountryViewHolder(view,delegate)
    }
}