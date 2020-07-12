

import android.content.Context
import androidx.room.Room
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mhst.architectureassignment.data.vos.BaseVO
import com.mhst.architectureassignment.data.vos.ScoreAndReviewVO
import com.mhst.travelassignmenttwo.persistance.daos.CoyntryDao
import com.mhst.travelassignmenttwo.persistance.daos.TourDao
import com.mhst.travelassignmenttwo.persistance.dbs.TourDb
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Moe Htet on 12,July,2020
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    lateinit var countryDao : CoyntryDao
    lateinit var tourDao : TourDao
    lateinit var tourDb : TourDb

    private val dummyList = mutableListOf<BaseVO>()
    private val reviewList = listOf(
        ScoreAndReviewVO("Name",4.5,5,10),
        ScoreAndReviewVO("Name",4.5,5,10),
        ScoreAndReviewVO("Name",4.5,5,10)
    )

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        tourDb = inMemoryDatabaseBuilder(
            context, TourDb::class.java
        ).build()
        countryDao = tourDb.countryDao()
        tourDao = tourDb.tourDao()
        for(i in 1..4){
           dummyList.add(
               BaseVO("name one","this is descritopn","yangon",
           4.5f,reviewList))
        }
    }

    @After
    fun closeDb(){
        tourDb.close()
    }

    @Test
    fun insertToCountry(){
        countryDao.insertAllCountries(dummyList)
        assert(countryDao.getAllTCountries().size == dummyList.size)
    }

    @Test
    fun insertToTour(){
        tourDao.insertAllTours(dummyList)
        assert(tourDao.getAllTours().size == dummyList.size)
    }


}