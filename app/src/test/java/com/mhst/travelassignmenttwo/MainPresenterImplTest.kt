package com.mhst.travelassignmenttwo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mhst.architectureassignment.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl
import com.mhst.travelassignmenttwo.data.vos.TourAndCountryVO
import com.mhst.travelassignmenttwo.mvp.presenters.MainPresenterImpl
import com.mhst.travelassignmenttwo.mvp.views.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Moe Htet on 11,July,2020
 */
@RunWith(AndroidJUnit4::class)
class MainPresenterImplTest {

    @RelaxedMockK
    lateinit var mView : MainView

    lateinit var mainPresenter: MainPresenterImpl

    lateinit var tourModel : TourModel

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        tourModel = TourModelImpl(ApplicationProvider.getApplicationContext())
        mainPresenter = MainPresenterImpl(ApplicationProvider.getApplicationContext())
        mainPresenter.initPresenter(mView)
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
    fun onUIReady_callDisplayList(){
        val lifeCycleOwner = mock(LifecycleOwner::class.java)
        val lifeCycleRegistry = LifecycleRegistry(lifeCycleOwner)
        lifeCycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(lifeCycleOwner.lifecycle).thenReturn(lifeCycleRegistry)

        mainPresenter.onUiReady(lifeCycleOwner)

       verifyAll {
           mView.showLoading()
           mView.showLists(TourAndCountryVO(listOf(), listOf()))
       }

    }

}