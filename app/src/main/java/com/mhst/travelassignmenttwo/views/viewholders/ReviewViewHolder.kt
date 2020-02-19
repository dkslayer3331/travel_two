package com.mhst.architectureassignment.views.viewholders

import android.view.View
import com.mhst.architectureassignment.R
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import kotlinx.android.synthetic.main.rv_score_and_review_item.view.*

class ReviewViewHolder(itemView: View) : BaseViewHolder<ScoreAndReviewVO>(itemView) {
    override fun binData(data: ScoreAndReviewVO?) {

        itemView.tvServiceName.text = data?.name ?: ""

       val score = itemView.context.getString(R.string.txt_max_get_score,data?.score,data?.maxScore)

        itemView.tvRatingGetAndMax.text = score

        val reviewCount = itemView.context.getString(R.string.text_total_review,data?.totalReviews)

        itemView.tvReviewCount.text = reviewCount

    }
}