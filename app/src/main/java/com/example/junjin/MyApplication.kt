package com.example.junjin

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tencent.mmkv.MMKV

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        MMKV.initialize(this)
    }
}