package com.mhst.architectureassignment.data.vos

import com.google.gson.annotations.SerializedName

data class BaseVO(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("description")
    val description : String = "",
    @SerializedName("location")
    val location : String = "",
    @SerializedName("average_rating")
    val avgRating : Float = 0F,
    @SerializedName("scores_and_reviews")
    val scoresAndReviews : List<ScoreAndReviewVO> = listOf(),
    @SerializedName("photos")
    val photos : List<String> = listOf()
)