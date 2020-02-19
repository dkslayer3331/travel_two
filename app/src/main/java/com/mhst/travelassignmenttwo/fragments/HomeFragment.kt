package com.mhst.architectureassignment.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.mhst.architectureassignment.adapters.CountryAdapter
import com.mhst.architectureassignment.adapters.TourAdapter
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.models.TourModelImpl
import com.mhst.architectureassignment.views.viewpods.EmptyViewPod
import com.mhst.travelassignmenttwo.DetailActivity
import com.mhst.travelassignmenttwo.R
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewPodEmpty : EmptyViewPod

    lateinit var countryAdapter: CountryAdapter

    lateinit var tourAdapter: TourAdapter

    val tourModel: TourModel = TourModelImpl

    private fun requestData() {
        swipeRefresh.isRefreshing = true
        tourModel.getAllList(onSucess = { countries, tours ->
            swipeRefresh.isRefreshing = false
            if(tours.isNotEmpty() && countries.isNotEmpty()){
                hideEmptyView()
                tourAdapter.setNewData(tours.toMutableList())
                countryAdapter.setNewData(countries.toMutableList())
            }
            else showEmptyView()

        }, onFail = {
            swipeRefresh.isRefreshing = false
            view?.let { it1 -> Snackbar.make(it1, it, Snackbar.LENGTH_LONG).show() }
            showEmptyView()
        })
    }

    private fun setupRecyclers() {
        rvTours.adapter = tourAdapter
        rvCountry.adapter = countryAdapter
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            requestData()
        }
    }

    private fun showEmptyView(){
//        ivEmptyImage.visibility = View.VISIBLE
//        tvEmptyText.visibility = View.VISIBLE
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewPodEmpty = vpEmpty as EmptyViewPod

        setupSwipeRefresh()

        requestData()

        countryAdapter = CountryAdapter {
            Log.d("countryPosition", it.toString())
            val intent = context?.let { it1 -> DetailActivity.newInstance(it1, it, 1) }
            startActivity(intent)
        }

        tourAdapter = TourAdapter {
            val intent = context?.let { it1 -> DetailActivity.newInstance(it1, it, 2) }
            startActivity(intent)
        }

        setupRecyclers()

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
}
