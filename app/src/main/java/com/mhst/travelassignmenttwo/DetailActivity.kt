package com.mhst.travelassignmenttwo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.adapters.PhotoAdapter
import com.mhst.architectureassignment.adapters.ReviewAdapter
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var scoreAndReviewAdapter: ReviewAdapter

    lateinit var photoAdapter: PhotoAdapter

   lateinit var tourModel : TourModel

    var data = BaseVO()

    fun bindCountry(data : CountrVO){
        tvTourHeading.text = data.name

        tvLocationName.text = data.location

        tvDesc.text = data.description

        Glide.with(this).load(data.photos[0]).into(ivBgImage)

        ratingBar.rating = data?.avgRating

        tvRating.text = data?.avgRating.toString()

        scoreAndReviewAdapter.setNewData(data.scoresAndReviews.toMutableList())

        photoAdapter.setNewData(data.photos.toMutableList())
    }

    fun bindTour(data : BaseVO){
        tvTourHeading.text = data.name

        tvLocationName.text = data.location

        tvDesc.text = data.description

        Glide.with(this).load(data.photos[0]).into(ivBgImage)

        ratingBar.rating = data?.avgRating

        tvRating.text = data?.avgRating.toString()

        scoreAndReviewAdapter.setNewData(data.scoresAndReviews.toMutableList())

        photoAdapter.setNewData(data.photos.toMutableList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tourModel = TourModelImpl(this)

        val name = intent.getStringExtra(IE_NAME)

        val type = intent.getIntExtra(IE_TYPE,0)

       // data = if(type == 1) tourModel.getCountryDetail("") else model.getTourDetail(id)
       if(type == 1){
        tourModel.getCountryDetail(name).observe(this, Observer {
            bindCountry(it)
        })
       }
        else {
           tourModel.tourDetail(name).observe(this, Observer {
              bindTour(it)
           })
       }

        setUpRecycler()

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
        private const val IE_NAME = "Name"
       private const val IE_TYPE = "Type"  // country -> 1 , tour -> 2

        fun newInstance(context: Context, name : String, type : Int) : Intent {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(IE_NAME,name)
            intent.putExtra(IE_TYPE,type)
            return intent
        }

    }


}
