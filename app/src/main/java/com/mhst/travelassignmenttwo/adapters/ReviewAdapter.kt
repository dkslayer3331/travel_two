package com.mhst.architectureassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import com.mhst.architectureassignment.views.viewholders.ReviewViewHolder
import com.mhst.travelassignmenttwo.R

class ReviewAdapter : BaseRecyclerAdapter<ScoreAndReviewVO, ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_score_and_review_item,parent,false)
        return  ReviewViewHolder(view)
    }
}