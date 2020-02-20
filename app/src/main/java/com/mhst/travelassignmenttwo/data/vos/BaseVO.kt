package com.mhst.architectureassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mhst.travelassignmenttwo.persistance.typeconverters.PhotoUrlConverter
import com.mhst.travelassignmenttwo.persistance.typeconverters.ScoreAndReviewTypeConverter

@TypeConverters(PhotoUrlConverter::class,ScoreAndReviewTypeConverter::class)
@Entity(tableName = "tours")
data class BaseVO(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
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