package com.mhst.architectureassignment.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_photo_item.view.*

class PhotoViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
    override fun binData(data: String?) {
        Glide.with(itemView.context).load(data).into(itemView.ivPhotoItem)
    }
}