package com.mhst.travelassignmenttwo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import com.mhst.travelassignmenttwo.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.data.vos.CountrVO
import com.mhst.travelassignmenttwo.mvp.presenters.DetailPresenterImpl
import com.mhst.travelassignmenttwo.mvp.views.DetailView
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

/**
 * Created by Moe Htet on 14,July,2020
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class DetailPresenterImplTest {

    @RelaxedMockK
    lateinit var mView : DetailView

   // lateinit var tourModel: TourModel

    lateinit var detailPresenterImpl: DetailPresenterImpl

    lateinit var baseVO: BaseVO

    lateinit var countryVO : CountrVO

    fun prepareDummyDatas(){
        val scoreAndReviewVO = ScoreAndReviewVO("Name",4.5,5,10)
        baseVO =  BaseVO("name one","this is description","yangon",
            4.5f, listOf(scoreAndReviewVO))
        countryVO = CountrVO(1,"myanmar","this is desc","da world",
            5f, listOf(scoreAndReviewVO), listOf("img1","img2"))
    }

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mView = mock(DetailView::class.java)
        detailPresenterImpl = DetailPresenterImpl()
        detailPresenterImpl.setUp(ApplicationProvider.getApplicationContext())
        detailPresenterImpl.initPresenter(mView)
        prepareDummyDatas()
    }

    @Test
    fun testLoadDetail(){
        val lifecycleOwner = mock(LifecycleOwner::class.java)
        val lifeCycleRegistry = LifecycleRegistry(lifecycleOwner)
        lifeCycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        Mockito.`when`(lifecycleOwner.lifecycle).thenReturn(lifeCycleRegistry)

       detailPresenterImpl.onUiReady(lifecycleOwner,countryVO.name,1)

        verify {
            //mView.showLoading(  )
            mView.countryDetail(countryVO)
        }
    }

}