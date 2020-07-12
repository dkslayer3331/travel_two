package com.mhst.travelassignmenttwo.ui_tests

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.mhst.travelassignmenttwo.MainActivity
import com.mhst.travelassignmenttwo.R
import com.mhst.travelassignmenttwo.views.viewholders.TourViewHolder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Moe Htet on 12,July,2020
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class GoToTourDetailTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp(){
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun tapTour_navigateDetail(){

        Thread.sleep(4000)

        onView(withId(R.id.rvTours))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TourViewHolder>(0,
                    click()
                ))

        onView(withId(R.id.tvTourHeading))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}