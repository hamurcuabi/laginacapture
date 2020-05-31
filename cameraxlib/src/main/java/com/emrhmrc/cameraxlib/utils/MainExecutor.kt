package com.emrhmrc.cameraxlib.utils

import android.os.Handler
import android.os.Looper
import com.emrhmrc.cameraxlib.utils.ThreadExecutor


class MainExecutor : ThreadExecutor(Handler(Looper.getMainLooper())) {

    override fun execute(runnable: Runnable) {
        handler.post(runnable)
    }
}