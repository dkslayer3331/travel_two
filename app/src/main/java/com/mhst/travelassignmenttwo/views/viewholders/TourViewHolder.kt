package com.mhst.architectureassignment.views.viewholders

import android.view.TouchDelegate
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewpods.FavViewPod
import kotlinx.android.synthetic.main.rating_viewpod.view.*
import kotlinx.android.synthetic.main.rv_tour_item.view.*

class TourViewHolder(itemView: View,val delegate: (String)->Unit) : BaseViewHolder<BaseVO>(itemView) {
    override fun binData(data: BaseVO?) {

        val vpScore = itemView.vpScore as FavViewPod

        itemView.tvTourName.text = data?.name ?: ""
        itemView.tvTourDesc.text = data?.description ?: ""

        vpScore.setValues(data?.avgRating ?: 0f)

        Glide.with(itemView.context).load(data?.photos?.get(0) ?: "").into(itemView.ivTourPhoto)

        itemView.setOnClickListener {
            delegate(data!!.name)
        }
    }
}