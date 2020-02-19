package com.mhst.travelassignmenttwo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
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
