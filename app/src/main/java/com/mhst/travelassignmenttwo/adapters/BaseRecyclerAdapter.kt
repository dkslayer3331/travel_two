package com.mhst.architectureassignment.adapters

import androidx.recyclerview.widget.RecyclerView
import com.mhst.architectureassignment.views.viewholders.BaseViewHolder

abstract class BaseRecyclerAdapter<W,V : BaseViewHolder<W>> : RecyclerView.Adapter<V>() {

    var mData :  MutableList<W> = arrayListOf()

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.binData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun setNewData(list : MutableList<W>){
        mData = list
        notifyDataSetChanged()
    }



}