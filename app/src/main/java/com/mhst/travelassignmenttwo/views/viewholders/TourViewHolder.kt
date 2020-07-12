package com.mhst.travelassignmenttwo.views.viewholders

import android.view.TouchDelegate
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewholders.BaseViewHolder
import com.mhst.architectureassignment.views.viewpods.FavViewPod
import com.mhst.travelassignmenttwo.delegates.TourDelegate
import kotlinx.android.synthetic.main.rv_tour_item.view.*

class TourViewHolder(itemView: View,val delegate: TourDelegate) : BaseViewHolder<BaseVO>(itemView) {

    override fun binData(data: BaseVO?) {

        val vpScore = itemView.vpScore as FavViewPod

        itemView.tvTourName.text = data?.name ?: ""
        itemView.tvTourDesc.text = data?.description ?: ""

        vpScore.setValues(data?.avgRating ?: 0f)

        Glide.with(itemView.context).load(data?.photos?.get(0) ?: "").into(itemView.ivTourPhoto)

        itemView.setOnClickListener {
           delegate.onTap(data!!.name,2)
        }
    }
}