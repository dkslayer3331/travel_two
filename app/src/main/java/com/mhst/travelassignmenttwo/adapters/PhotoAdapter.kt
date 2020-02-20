package com.mhst.architectureassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mhst.architectureassignment.views.viewholders.PhotoViewHolder
import com.mhst.travelassignmenttwo.R

class PhotoAdapter : BaseRecyclerAdapter<String, PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_photo_item,parent,false)
        return  PhotoViewHolder(view)
    }
}