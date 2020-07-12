package com.mhst.travelassignmenttwo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mhst.architectureassignment.adapters.BaseRecyclerAdapter
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.R
import com.mhst.travelassignmenttwo.delegates.TourDelegate
import com.mhst.travelassignmenttwo.views.viewholders.TourViewHolder

class TourAdapter(val  delegate: TourDelegate) : BaseRecyclerAdapter<BaseVO, TourViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_tour_item,parent,false)
        return TourViewHolder(view,delegate)
    }
}