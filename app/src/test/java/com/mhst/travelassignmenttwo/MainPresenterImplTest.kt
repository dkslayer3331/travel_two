package com.mhst.travelassignmenttwo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.mvp.presenters.MainPresenterImpl
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.mockk.verifyAll
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config


/**
 * Created by Moe Htet on 11,July,2020
 */
@RunWith(AndroidJUnit4::class)
@Config(manifest=Config.NONE)
class MainPresenterImplTest {

    @RelaxedMockK
    lateinit var mView : MainView

    private lateinit var mainPresenter: MainPresenterImpl

    private lateinit var tourModel : TourModel

    lateinit var testScheduler : TestScheduler

    private val dummyList = mutableListOf<BaseVO>()
    private val reviewList = listOf(
        ScoreAndReviewVO("Name",4.5,5,10),
        ScoreAndReviewVO("Name",4.5,5,10),
        ScoreAndReviewVO("Name",4.5,5,10)
    )

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
       testScheduler = TestScheduler()
        mView = mock(MainView::class.java)
     //   tourModel = TourModelImpl(ApplicationProvider.getApplicationContext())
        mainPresenter = MainPresenterImpl()
        mainPresenter.setUp(ApplicationProvider.getApplicationContext(),testScheduler,testScheduler)
        mainPresenter.initPresenter(mView)
        for(i in 1..4){
            dummyList.add(
                BaseVO("name one","this is description","yangon",
                    4.5f,reviewList))
        }
    }

    @Test
    fun onTap_callNavigateDetail(){
        val name = "Name"
        val type = 1
        mainPresenter.onTap(name,type)
        verify {
            mView.navigateDetail(name,type)
        }
    }

    @Test
    fun loadDataTest(){
        tourModel.combined().subscribeOn(testScheduler).observeOn(testScheduler)
            .subscribe {
                if(it.countries.isEmpty() && it.tours.isEmpty()) mView.displayEmptyView()
                else {
                    mView.showLists(it)
                }
            }
    }

    @Test
    fun onUIReady_callDisplayList(){

        val mockedResponse = TourAndCountryVO(dummyList,dummyList)

        tourModel = mock(TourModel::class.java)

        val lifeCycleOwner = mock(LifecycleOwner::class.java)
        val lifeCycleRegistry = LifecycleRegistry(lifeCycleOwner)
        lifeCycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(lifeCycleOwner.lifecycle).thenReturn(lifeCycleRegistry)

        doReturn(Observable.just(mockedResponse))
            .`when`(tourModel)
            .combined()

        mainPresenter.onUiReady(lifeCycleOwner)
        testScheduler.triggerActions()

        //mainPresenter.onUiReady(lifeCycleOwner)

        verify(mView).showLoading()
        verify(mView).showLists(TourAndCountryVO(dummyList,dummyList))

    }

}