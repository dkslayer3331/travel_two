package com.mhst.architectureassignment.adapters

import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.ViewGroup
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewholders.TourViewHolder
import com.mhst.travelassignmenttwo.R

class TourAdapter(val  delegate: (Int)-> Unit) : BaseRecyclerAdapter<BaseVO, TourViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_tour_item,parent,false)
        return TourViewHolder(view,delegate)
    }
}