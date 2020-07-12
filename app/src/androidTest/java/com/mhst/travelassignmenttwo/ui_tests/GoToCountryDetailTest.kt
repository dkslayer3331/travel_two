package com.mhst.travelassignmenttwo.ui_tests

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.mhst.travelassignmenttwo.MainActivity
import com.mhst.travelassignmenttwo.R
import com.mhst.travelassignmenttwo.views.viewholders.CountryViewHolder
import first
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Moe Htet on 12,July,2020
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class GoToCountryDetailTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp(){
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun swipeRefreshTest(){
        onView(withId(R.id.swipeRefresh)).perform(swipeDown())
    }

    @Test
    fun tapCountry_navigateDetail(){

        Thread.sleep(4000)

        onView(withId(R.id.rvCountry))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CountryViewHolder>(0, click()))

        onView(withId(R.id.tvTourHeading))
            .check(matches(isDisplayed()))
    }

}