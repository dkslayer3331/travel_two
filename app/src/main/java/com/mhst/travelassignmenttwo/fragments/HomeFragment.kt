package com.mhst.architectureassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mhst.architectureassignment.adapters.CountryAdapter
import com.mhst.travelassignmenttwo.adapters.TourAdapter
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.architectureassignment.views.viewpods.EmptyViewPod
import com.mhst.travelassignmenttwo.DetailActivity
import com.mhst.travelassignmenttwo.R
import com.mhst.travelassignmenttwo.TourApp
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.mvp.presenters.MainPresenter
import com.mhst.travelassignmenttwo.mvp.presenters.MainPresenterImpl
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() , MainView{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var presenter : MainPresenter

    lateinit var viewPodEmpty : EmptyViewPod

    lateinit var countryAdapter: CountryAdapter

    lateinit var tourAdapter: TourAdapter

     lateinit var tourModel: TourModel

    private fun setupRecyclers() {
        rvTours.adapter = tourAdapter
        rvCountry.adapter = countryAdapter
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            presenter.onSwipeRrfresh(this)
        }
    }

    private fun showEmptyView(){
        viewPodEmpty.visibility = View.VISIBLE
        viewPodEmpty.setEmptyData("Something wrong","https://cdn2.iconfinder.com/data/icons/files-and-documents-12/120/books_2f4r-512.png")

    }

    private fun hideEmptyView(){
        viewPodEmpty.visibility = View.GONE
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenter = ViewModelProviders.of(activity!!).get(MainPresenterImpl::class.java)

        presenter.initPresenter(this)

        (presenter as MainPresenterImpl).setUp(TourApp.context,Schedulers.io(),AndroidSchedulers.mainThread())

        tourModel = TourModelImpl(TourApp.context)

        viewPodEmpty = vpEmpty as EmptyViewPod

        setupSwipeRefresh()

        countryAdapter = CountryAdapter(presenter)

        tourAdapter = TourAdapter(presenter)

        setupRecyclers()

        presenter.onUiReady(this)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }


    override fun showLists(list: TourAndCountryVO) {
        hideEmptyView()
        tourAdapter.setNewData(list.tours.toMutableList())
        countryAdapter.setNewData(list.countries.toMutableList())
    }

    override fun showErrorMessage(message: String) {
        view?.let {
            Snackbar.make(it,message,Snackbar.LENGTH_LONG).show()
        }
    }

    override fun displayEmptyView() {
       showEmptyView()
    }

    override fun navigateDetail(name: String, type: Int) {
            context?.let {
                startActivity(DetailActivity.newInstance(it,name,type))
            }
    }
}
