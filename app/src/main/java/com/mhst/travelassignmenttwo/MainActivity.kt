package com.mhst.travelassignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhst.architectureassignment.fragments.HomeFragment

class MainActivity : BaseActivity() {

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
            HomeFragment.newInstance("","")).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }
}
