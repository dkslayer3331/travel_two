package com.mhst.travelassignmenttwo.activities

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Moe Htet on 22,February,2020
 */
open class BaseActivity : AppCompatActivity() {

    fun showSnackBar(message : String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }

}