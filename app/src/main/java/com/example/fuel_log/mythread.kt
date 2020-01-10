package com.example.fuel_log

import android.os.Handler
import android.os.HandlerThread

class mythreads (threadname : String): HandlerThread(threadname) {

    private lateinit var mhand : Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mhand = Handler(looper)
    }

    fun postTask(tesk : Runnable)
    {
        mhand.post(tesk)
    }

}