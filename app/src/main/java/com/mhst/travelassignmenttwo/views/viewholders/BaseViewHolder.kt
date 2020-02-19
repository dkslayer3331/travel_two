package com.mhst.architectureassignment.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mData : List<T>? = null

    abstract fun binData(data : T?)

}