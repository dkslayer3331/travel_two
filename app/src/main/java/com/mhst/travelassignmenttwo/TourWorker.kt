package com.mhst.travelassignmenttwo

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mhst.travelassignmenttwo.data.models.TourModel
import com.mhst.travelassignmenttwo.data.models.TourModelImpl

/**
 * Created by Moe Htet on 15,March,2020
 */
class TourWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    val tourModel : TourModel = TourModelImpl(context)

    override fun doWork(): Result {
        var result = Result.failure()

        tourModel.getFromApiAndSaveLocal(onSuccess = {
            result = Result.success()
        },onError = {
            result = Result.failure()
        })
        return result
    }
}