package com.mhst.travelassignmenttwo.activities

import android.os.Bundle
import com.mhst.architectureassignment.fragments.HomeFragment
import com.mhst.travelassignmenttwo.R

class MainActivity : BaseActivity() {

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainer,
            HomeFragment.newInstance("","")).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }
}
