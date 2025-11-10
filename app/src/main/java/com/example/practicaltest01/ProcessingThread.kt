package com.example.practicaltest01

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Process
import android.provider.SyncStateContract
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.Date
import java.util.Random
import kotlin.math.sqrt



class ProcessingThread(private val context: Context) :
    Thread() {
    private var isRunning = true
    private val random = Random()


    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun run() {
        Log.d(
            "Thread_Process",
            "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid()
        )
        while (isRunning) {
            sendMessage()
            sleep()
        }
        Log.d("Thread_Process", "Thread has stopped!")
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun sendMessage() {

        val n1 = random.nextInt(0, 100)
        val n2 = random.nextInt(0, 100)
        val n3 = random.nextInt(0, 100)
        val n4 = random.nextInt(0, 100)


        val intent = Intent()
        intent.setAction("Processing Thread")
        intent.putExtra("input1", n1)
        intent.putExtra("input2", n2)
        intent.putExtra("input3", n3)
        intent.putExtra("input4", n4)
        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(1000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}