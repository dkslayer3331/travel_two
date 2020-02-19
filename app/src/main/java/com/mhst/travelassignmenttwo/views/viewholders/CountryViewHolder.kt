package com.mhst.architectureassignment.views.viewholders

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.views.viewpods.FavViewPod
import kotlinx.android.synthetic.main.rv_country_item.view.*

class CountryViewHolder(itemView: View,val delegate : (id : Int)->Unit) : BaseViewHolder<BaseVO>(itemView) {

    override fun binData(data: BaseVO?) {

        val vp = itemView.vpFavCountry as FavViewPod

        vp.setBackgroundColor(Color.parseColor("#CCFFFFFF"))

        itemView.tvCountryName.text = data?.name ?: "Not avail"

        Glide.with(itemView.context).load(data?.photos?.get(0)).into(itemView.ivCountryImage)

        vp.setValues(data?.avgRating ?: 0f,Color.parseColor("#CCFFFFFF"))

        itemView.setOnClickListener {
            delegate.invoke(adapterPosition)
        }
    }
}