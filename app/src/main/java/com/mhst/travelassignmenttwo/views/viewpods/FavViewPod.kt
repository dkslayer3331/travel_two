package com.mhst.architectureassignment.views.viewpods

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.mhst.architectureassignment.R
import kotlinx.android.synthetic.main.rating_viewpod.view.*

class FavViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setValues(score : Float, color : Int = ContextCompat.getColor(context,R.color.colorPrimary)){
        tvRating.text = score.toString()
        //vpFav.setBackgroundColor(color)
       // vpFav.setBackgroundDrawable(drawable)
    }

}