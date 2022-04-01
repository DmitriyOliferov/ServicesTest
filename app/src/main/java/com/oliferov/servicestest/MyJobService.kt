package com.oliferov.servicestest

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyJobService : JobService() {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        log("onStartJob")
        coroutine.launch {
            for(i in 0 until 100){
                delay(1000)
                log("Timer $i")
            }
            jobFinished(params,true)
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
        log("onDestroy")
    }

    private fun log(message: String){
        Log.i("SERVICE_TAG", "MyService: $message")
    }
}