package com.mhst.architectureassignment.data.vos

import com.google.gson.annotations.SerializedName

data class ScoreAndReviewVO(
    @SerializedName("name")
    val name : String,
    @SerializedName("score")
    val score : Double,
    @SerializedName("max_score")
    val maxScore : Int,
    @SerializedName("total_reviews")
    val totalReviews : Int
)