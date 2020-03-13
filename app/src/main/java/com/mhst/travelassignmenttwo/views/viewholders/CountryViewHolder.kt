package com.mhst.travelassignmenttwo.views.viewholders

import android.graphics.Color
import android.view.View
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewholders.BaseViewHolder
import com.mhst.architectureassignment.views.viewpods.FavViewPod
import com.mhst.travelassignmenttwo.delegates.TourDelegate
import kotlinx.android.synthetic.main.rv_country_item.view.*

class CountryViewHolder(itemView: View,val delegate: TourDelegate) : BaseViewHolder<BaseVO>(itemView) {

    override fun binData(data: BaseVO?) {

        val vp = itemView.vpFavCountry as FavViewPod

        vp.setBackgroundColor(Color.parseColor("#CCFFFFFF"))

        itemView.tvCountryName.text = data?.name ?: "Not avail"

        Glide.with(itemView.context).load(data?.photos?.get(0)).into(itemView.ivCountryImage)

        vp.setValues(data?.avgRating ?: 0f,Color.parseColor("#CCFFFFFF"))

        itemView.setOnClickListener {
            delegate.onTap(data!!.name,1)  //todo : change this
        }
    }
}