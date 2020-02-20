package com.mhst.travelassignmenttwo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhst.architectureassignment.adapters.PhotoAdapter
import com.mhst.architectureassignment.adapters.ReviewAdapter
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.architectureassignment.data.vos.BaseVO
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var scoreAndReviewAdapter: ReviewAdapter

    lateinit var photoAdapter: PhotoAdapter

    lateinit var tourModel : TourModel

    var data  = BaseVO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tourModel = TourModelImpl(this)

        val id = intent.getIntExtra(IE_ID,0)

        val type = intent.getIntExtra(IE_TYPE,0)

       // data = if(type == 1) tourModel.getCountryDetail("") else model.getTourDetail(id)

        setUpRecycler()

        tvTourHeading.text = data.name

        tvLocationName.text = data.location

        tvDesc.text = data.description

        Glide.with(this).load(data.photos[0]).into(ivBgImage)

        ratingBar.rating = data?.avgRating

        tvRating.text = data?.avgRating.toString()

        scoreAndReviewAdapter.setNewData(data.scoresAndReviews.toMutableList())

        photoAdapter.setNewData(data.photos.toMutableList())

        idBack.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecycler(){
        scoreAndReviewAdapter = ReviewAdapter()
        photoAdapter = PhotoAdapter()
        rvScoreAndReview.adapter = scoreAndReviewAdapter
        rvPhotos.adapter = photoAdapter
    }

    companion object{
        private const val IE_ID = "Id"
       private const val IE_TYPE = "Type"  // country -> 1 , tour -> 2

        fun newInstance(context: Context, id : Int, type : Int) : Intent {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(IE_ID,id)
            intent.putExtra(IE_TYPE,type)
            return intent
        }

    }


}
