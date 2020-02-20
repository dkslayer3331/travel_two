package com.mhst.travelassignmenttwo.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import com.mhst.travelassignmenttwo.persistance.typeconverters.PhotoUrlConverter
import com.mhst.travelassignmenttwo.persistance.typeconverters.ScoreAndReviewTypeConverter

/**
 * Created by Moe Htet on 20,February,2020
 */
@TypeConverters(PhotoUrlConverter::class, ScoreAndReviewTypeConverter::class)
@Entity(tableName = "countries")
data class CountrVO(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
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