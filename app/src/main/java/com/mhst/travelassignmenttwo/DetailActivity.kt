package com.mhst.travelassignmenttwo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.view.menu.MenuPresenter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mhst.architectureassignment.adapters.PhotoAdapter
import com.mhst.architectureassignment.adapters.ReviewAdapter
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.mvp.presenters.DetailPresenter
import com.mhst.travelassignmenttwo.mvp.presenters.DetailPresenterImpl
import com.mhst.travelassignmenttwo.mvp.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(),DetailView {

    lateinit var scoreAndReviewAdapter: ReviewAdapter

    lateinit var photoAdapter: PhotoAdapter

    lateinit var presenter: DetailPresenter

    var data = BaseVO()

    private fun setupPresenter(){
        presenter = ViewModelProviders.of(this).get(DetailPresenterImpl::class.java)
        presenter.initPresenter(this)
    }

    private fun bindCountry(data : CountrVO){
        tvTourHeading.text = data.name

        tvLocationName.text = data.location

        tvDesc.text = data.description

        Glide.with(this).load(data.photos[0]).into(ivBgImage)

        ratingBar.rating = data?.avgRating

        tvRating.text = data?.avgRating.toString()

        scoreAndReviewAdapter.setNewData(data.scoresAndReviews.toMutableList())

        photoAdapter.setNewData(data.photos.toMutableList())
    }

    private fun bindTour(data : BaseVO){
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

        setupPresenter()

        val name = intent.getStringExtra(IE_NAME)

        val type = intent.getIntExtra(IE_TYPE,0)

        setUpRecycler()

        presenter.onUiReady(this,name,type)

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

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showDetail(detailObj: BaseVO) {
        if(detailObj == null) Log.d("empty","tour null")
        bindTour(detailObj)
    }

    override fun countryDetail(detailObj: CountrVO) {
        if(detailObj == null) Log.d("empty","country null")
        bindCountry(detailObj)
    }


}
